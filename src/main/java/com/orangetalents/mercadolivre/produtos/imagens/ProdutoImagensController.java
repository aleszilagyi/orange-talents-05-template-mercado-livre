package com.orangetalents.mercadolivre.produtos.imagens;

import com.orangetalents.mercadolivre.compartilhado.credenciais.BuscaUsuario;
import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.produtos.ProdutoDto;
import com.orangetalents.mercadolivre.seguranca.UsuarioLogadoDetails;
import com.orangetalents.mercadolivre.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoImagensController {
    @Autowired
    private FakeUploadImagens fakeUploadImagens;
    @Autowired
    private BuscaUsuario buscaUsuario;
    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<ProdutoDto> uploadImagem(@PathVariable("id") Long idProduto, @Valid NovaImagemRequest request, @AuthenticationPrincipal UsuarioLogadoDetails usuarioLogado) {
        Usuario usuario = buscaUsuario.retornaUsuario(manager, usuarioLogado.getUsername());
        Produto produto = manager.find(Produto.class, idProduto);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        buscaUsuario.usuariosDevemSerIguais(usuario, produto.getUsuario());

        Set<String> listaUrlImagens = request.getImagens().stream().map(fakeUploadImagens::criaUrlImagem).collect(Collectors.toSet());
        Set<ImagemProduto> listaImagens = listaUrlImagens.stream().map(ImagemProduto::new).collect(Collectors.toSet());

        produto.setImagensProduto(listaImagens);
        manager.merge(produto);

        return ResponseEntity.ok(new ProdutoDto(produto));
    }
}
