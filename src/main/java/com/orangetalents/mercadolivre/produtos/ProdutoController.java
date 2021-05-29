package com.orangetalents.mercadolivre.produtos;

import com.orangetalents.mercadolivre.categorias.Categoria;
import com.orangetalents.mercadolivre.categorias.CategoriaRepository;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import com.orangetalents.mercadolivre.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid FormProdutoRequest formProdutoRequest, @AuthenticationPrincipal UserDetails usuarioLogado) {
        Categoria categoria = categoriaRepository.getById(formProdutoRequest.getIdCategoria());
        Usuario usuario = usuarioRepository.findByUsername(usuarioLogado.getUsername()).get();
        Produto produto = formProdutoRequest.converter(categoria, usuario);
        produtosRepository.save(produto);

        return ResponseEntity.ok(new ProdutoDto(produto));
    }
}
