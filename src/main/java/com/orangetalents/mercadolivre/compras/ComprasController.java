package com.orangetalents.mercadolivre.compras;

import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.produtos.ProdutosRepository;
import com.orangetalents.mercadolivre.seguranca.UsuarioLogadoDetails;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import com.orangetalents.mercadolivre.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@Validated
public class ComprasController {
    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ComprasRepository comprasRepository;

    @PostMapping("/{id}/compra")
    @Transactional
    public ResponseEntity iniciaCompra(@RequestBody @Valid CompraFormRequest request, @PathVariable("id") Long idProduto, @AuthenticationPrincipal UsuarioLogadoDetails usuarioLogado) throws BindException {
        Usuario usuario = usuarioRepository.findByUsername(usuarioLogado.getUsername()).get();
        Optional<Produto> talvezProduto = produtosRepository.findById(idProduto);
        if (talvezProduto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (talvezProduto.get().getUsuario().getId() == usuario.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Produto produto = talvezProduto.get();
        Compra compra = request.converter(usuario, produto);

        produto.abateQuantidade(request.getQuantidade());

        comprasRepository.save(compra);

        URI linkRedir = compra.getFormaPagamento().getLink(compra.getId());
        HttpHeaders header = new HttpHeaders();
        header.setLocation(linkRedir);

        return new ResponseEntity<>(header, HttpStatus.FOUND);
    }
}
