package com.orangetalents.mercadolivre.categorias;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid FormCategoria request) {
        Optional<Long> talvezCategoriaMaeId = Optional.ofNullable(request.getCategoriaMaeId());
        Categoria categoriaMae = null;
        if (talvezCategoriaMaeId.isPresent()) {
            categoriaMae = manager.find(Categoria.class, talvezCategoriaMaeId);
        }

        Categoria categoria = request.converter(categoriaMae);

        manager.persist(categoria);

        return ResponseEntity.ok(new CategoriaDto(categoria));
    }
}
