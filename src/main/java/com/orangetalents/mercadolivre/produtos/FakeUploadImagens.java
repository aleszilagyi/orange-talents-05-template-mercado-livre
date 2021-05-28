package com.orangetalents.mercadolivre.produtos;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;


@Component
public class FakeUploadImagens {

    public String criaUrlImagem(MultipartFile file) {
        String url = "https://bucket.io/" + LocalDateTime.now().toString() + file.getOriginalFilename();
        return url;
    }
}
