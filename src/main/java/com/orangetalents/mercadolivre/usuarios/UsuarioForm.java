package com.orangetalents.mercadolivre.usuarios;

import com.orangetalents.mercadolivre.compartilhado.anotacoes.UniqueValue;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UsuarioForm {
    @Email
    @NotBlank
    @UniqueValue(domainClass = Usuario.class, fieldName = "username")
    private String username;
    @NotBlank
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
