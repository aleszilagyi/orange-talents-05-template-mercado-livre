package com.orangetalents.mercadolivre.usuarios;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;
    @NotBlank
    @Column(nullable = false)
    private String passwordHash;
    @CreationTimestamp
    private LocalDateTime momentoCriacao;

    @Deprecated
    public Usuario() {
    }

    public Usuario(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
