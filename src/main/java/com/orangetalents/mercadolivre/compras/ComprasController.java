package com.orangetalents.mercadolivre.compras;

import com.orangetalents.mercadolivre.compartilhado.credenciais.BuscaUsuario;
import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.seguranca.UsuarioLogadoDetails;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/produtos")
@Validated
public class ComprasController {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private BuscaUsuario buscaUsuario;


    @PostMapping("/{id}/compra")
    @Transactional
    public ResponseEntity iniciaCompra(@RequestBody @Valid CompraFormRequest request, @PathVariable("id") Long idProduto, @AuthenticationPrincipal UsuarioLogadoDetails usuarioLogado) throws BindException {
        Usuario usuario = buscaUsuario.retornaUsuario(entityManager, usuarioLogado.getUsername());
        Produto produto = entityManager.find(Produto.class, idProduto);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        buscaUsuario.usuariosDevemSerDiferentes(usuario, produto.getUsuario());

        Compra compra = request.converter(usuario, produto);
        produto.abateQuantidade(request.getQuantidade());

        entityManager.persist(compra);

        URI linkRedir = compra.getFormaPagamento().getLink(compra.getId());
        HttpHeaders header = new HttpHeaders();
        header.setLocation(linkRedir);

        return new ResponseEntity<>(header, HttpStatus.FOUND);
    }
}
