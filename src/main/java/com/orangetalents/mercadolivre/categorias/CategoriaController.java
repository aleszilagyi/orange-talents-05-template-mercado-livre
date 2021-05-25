package com.orangetalents.mercadolivre.categorias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid FormCategoria request) {
        Categoria categoria = request.converter(categoriaRepository);
        categoriaRepository.save(categoria);

        return ResponseEntity.ok(new CategoriaDto(categoria));
    }
}
