package com.orangetalents.mercadolivre.produtos;

import com.orangetalents.mercadolivre.categorias.Categoria;
import com.orangetalents.mercadolivre.compartilhado.credenciais.BuscaUsuario;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private BuscaUsuario buscaUsuario;

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid FormProdutoRequest formProdutoRequest, @AuthenticationPrincipal UserDetails usuarioLogado) {
        Usuario usuario = buscaUsuario.retornaUsuario(manager, usuarioLogado.getUsername());

        Categoria categoria = manager.find(Categoria.class, formProdutoRequest.getIdCategoria());

        Produto produto = formProdutoRequest.converter(categoria, usuario);
        manager.persist(produto);

        return ResponseEntity.ok(new ProdutoDto(produto));
    }
}
