package com.orangetalents.mercadolivre.produtos.imagens;

import com.orangetalents.mercadolivre.config.seguranca.UsuarioLogadoDetails;
import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.produtos.ProdutoDto;
import com.orangetalents.mercadolivre.produtos.ProdutosRepository;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import com.orangetalents.mercadolivre.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoImagensController {
    @Autowired
    private FakeUploadImagens fakeUploadImagens;
    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<ProdutoDto> uploadImagem(@PathVariable("id") Long idProduto, @Valid NovaImagemRequest request, @AuthenticationPrincipal UsuarioLogadoDetails usuarioLogado) {
        Usuario usuario = usuarioRepository.findByUsername(usuarioLogado.getUsername()).get();
        Optional<Produto> talvezProduto = produtosRepository.findById(idProduto);
        if(talvezProduto.isEmpty()){
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
