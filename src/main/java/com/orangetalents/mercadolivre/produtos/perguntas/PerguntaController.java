package com.orangetalents.mercadolivre.produtos.perguntas;

import com.orangetalents.mercadolivre.config.seguranca.UsuarioLogadoDetails;
import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.produtos.ProdutosRepository;
import com.orangetalents.mercadolivre.produtos.email.EnviaEmail;
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
@RequestMapping("/produtos/{id}/perguntas")
public class PerguntaController {
    @Autowired
    private PerguntasRepository perguntasRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private EnviaEmail enviaEmail;

    @PostMapping
    @Transactional
    public ResponseEntity<PerguntaDto> registrar(@RequestBody @Valid FormPerguntaRequest request, @AuthenticationPrincipal UsuarioLogadoDetails usuarioLogado, @PathVariable("id") Long idProduto) {
        Usuario usuario = usuarioRepository.findByUsername(usuarioLogado.getUsername()).get();
        Optional<Produto> talvezProduto = produtosRepository.findById(idProduto);
        if (talvezProduto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (talvezProduto.get().getUsuario().getId() == usuario.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Pergunta pergunta = request.converter(usuario, talvezProduto.get());

        perguntasRepository.save(pergunta);
        enviaEmail.EnviaEmailPadraoPergunta(pergunta);

        return ResponseEntity.ok(new PerguntaDto(pergunta));
    }
}
