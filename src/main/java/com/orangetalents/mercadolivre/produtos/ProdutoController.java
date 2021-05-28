package com.orangetalents.mercadolivre.produtos;

import com.orangetalents.mercadolivre.categorias.Categoria;
import com.orangetalents.mercadolivre.categorias.CategoriaRepository;
import com.orangetalents.mercadolivre.config.seguranca.UsuarioLogadoDetails;
import com.orangetalents.mercadolivre.produtos.imagens.FakeUploadImagens;
import com.orangetalents.mercadolivre.produtos.imagens.ImagemProduto;
import com.orangetalents.mercadolivre.produtos.imagens.NovaImagemRequest;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import com.orangetalents.mercadolivre.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private FakeUploadImagens fakeUploadImagens;

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid FormProdutoRequest formProdutoRequest, @AuthenticationPrincipal UserDetails usuarioLogado) {
        Categoria categoria = categoriaRepository.getById(formProdutoRequest.getIdCategoria());
        Usuario usuario = usuarioRepository.findByUsername(usuarioLogado.getUsername()).get();
        Produto produto = formProdutoRequest.converter(categoria, usuario);
        produtosRepository.save(produto);

        return ResponseEntity.ok(new ProdutoDto(produto));
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<ProdutoDto> uploadImagem(@PathVariable("id") Long idProduto, @Valid NovaImagemRequest request, @AuthenticationPrincipal UsuarioLogadoDetails usuarioLogado) {
        Usuario usuario = usuarioRepository.findByUsername(usuarioLogado.getUsername()).get();
        Optional<Produto> talvezProduto = produtosRepository.findById(idProduto);
        if (talvezProduto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (talvezProduto.get().getUsuario().getId() != usuario.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Produto produto = talvezProduto.get();
        Set<String> listaUrlImagens = request.getImagens().stream().map(file ->
                fakeUploadImagens.criaUrlImagem(file)).collect(Collectors.toSet());

        Set<ImagemProduto> listaImagens = listaUrlImagens.stream().map(ImagemProduto::new).collect(Collectors.toSet());

        produto.setImagensProduto(listaImagens);
        return ResponseEntity.ok(new ProdutoDto(produto));
    }
}
