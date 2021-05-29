package com.orangetalents.mercadolivre.produtos.detalhesProduto;

import com.orangetalents.mercadolivre.produtos.Produto;
import com.orangetalents.mercadolivre.produtos.ProdutoDto;
import com.orangetalents.mercadolivre.produtos.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class DetalhesProdutoController {
    @Autowired
    private ProdutosRepository produtosRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> buscaDetalhesProduto(@PathVariable("id") Long idProduto) {
        Optional<Produto> talvezProduto = produtosRepository.findById(idProduto);
        if (talvezProduto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ProdutoDto(talvezProduto.get()));
    }
}
