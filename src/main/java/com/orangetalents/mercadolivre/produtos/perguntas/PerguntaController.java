package com.orangetalents.mercadolivre.produtos.perguntas;

import com.orangetalents.mercadolivre.compartilhado.credenciais.BuscaUsuario;
import com.orangetalents.mercadolivre.outrosSistemas.email.EnviaEmail;
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
@RequestMapping("/produtos/{id}/perguntas")
public class PerguntaController {
    @Autowired
    private EnviaEmail enviaEmail;
    @Autowired
    private BuscaUsuario buscaUsuario;
    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<PerguntaDto> registrar(@RequestBody @Valid FormPerguntaRequest request, @AuthenticationPrincipal UsuarioLogadoDetails usuarioLogado, @PathVariable("id") Long idProduto) {
        Usuario usuario = buscaUsuario.retornaUsuario(entityManager, usuarioLogado.getUsername());
        Produto produto = entityManager.find(Produto.class, idProduto);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        buscaUsuario.usuariosDevemSerDiferentes(usuario, produto.getUsuario());

        Pergunta pergunta = request.converter(usuario, produto);

        entityManager.persist(pergunta);
        enviaEmail.EnviaEmailPadraoPergunta(pergunta);

        return ResponseEntity.ok(new PerguntaDto(pergunta));
    }
}
