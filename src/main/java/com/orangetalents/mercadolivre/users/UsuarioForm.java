package com.orangetalents.mercadolivre.users;

import com.orangetalents.mercadolivre.config.anotacoes.UniqueValue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.*;


public class UsuarioForm {
    @Email
    @NotBlank
    @UniqueValue(domainClass = Usuario.class, fieldName = "username")
    private String username;
    @Size(min = 6)
    private String password;

    public UsuarioForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Usuario converter() {
        password = new BCryptPasswordEncoder().encode(password);
        return new Usuario(username, password);
    }
}
