package com.orangetalents.mercadolivre.produtos.opinioes;

import com.orangetalents.mercadolivre.seguranca.UsuarioLogadoDetails;
import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.produtos.ProdutosRepository;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import com.orangetalents.mercadolivre.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class OpiniaoController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OpiniaoRepository opiniaoRepository;
    @Autowired
    private ProdutosRepository produtosRepository;

    @PostMapping("/{id}/opinioes")
    @Transactional
    public ResponseEntity<OpiniaoDto> cadastrar(@PathVariable("id") Long idProduto, @RequestBody @Valid FormOpiniaoRequest request, @AuthenticationPrincipal UsuarioLogadoDetails usuarioLogado) {
        Usuario usuario = usuarioRepository.findByUsername(usuarioLogado.getUsername()).get();
        Optional<Produto> talvezProduto = produtosRepository.findById(idProduto);
        if (talvezProduto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (talvezProduto.get().getUsuario().getId() == usuario.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Opiniao opiniao = request.converter(talvezProduto.get(), usuario);
        opiniaoRepository.save(opiniao);

        return ResponseEntity.ok(new OpiniaoDto(opiniao));
    }
}
