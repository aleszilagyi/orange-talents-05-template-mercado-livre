package com.orangetalents.mercadolivre.produtos.detalhesProduto;

import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.produtos.ProdutoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/produtos")
public class DetalhesProdutoController {
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> buscaDetalhesProduto(@PathVariable("id") Long idProduto) {
        Produto produto = entityManager.find(Produto.class, idProduto);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ProdutoDto(produto));
    }
}
