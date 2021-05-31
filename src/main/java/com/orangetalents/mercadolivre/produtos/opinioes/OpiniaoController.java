package com.orangetalents.mercadolivre.produtos.opinioes;

import com.orangetalents.mercadolivre.compartilhado.credenciais.BuscaUsuario;
import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.seguranca.UsuarioLogadoDetails;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class OpiniaoController {
    @Autowired
    private BuscaUsuario buscaUsuario;
    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/{id}/opinioes")
    @Transactional
    public ResponseEntity<OpiniaoDto> cadastrar(@PathVariable("id") Long idProduto, @RequestBody @Valid FormOpiniaoRequest request, @AuthenticationPrincipal UsuarioLogadoDetails usuarioLogado) {
        Usuario usuario = buscaUsuario.retornaUsuario(manager, usuarioLogado.getUsername());
        Produto produto = manager.find(Produto.class, idProduto);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        buscaUsuario.usuariosDevemSerDiferentes(usuario, produto.getUsuario());

        Opiniao opiniao = request.converter(produto, usuario);
        manager.persist(opiniao);

        return ResponseEntity.ok(new OpiniaoDto(opiniao));
    }
}
