package com.orangetalents.mercadolivre.categorias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid FormCategoria request) {
        Optional<Long> talvezCategoriaMaeId = Optional.ofNullable(request.getCategoriaMaeId());
        Categoria categoriaMae = null;
        if (talvezCategoriaMaeId.isPresent()) {
            categoriaMae = categoriaRepository.findById(talvezCategoriaMaeId.get()).get();
        }
        Categoria categoria = request.converter(categoriaMae);
        categoriaRepository.save(categoria);

        return ResponseEntity.ok(new CategoriaDto(categoria));
    }
}
