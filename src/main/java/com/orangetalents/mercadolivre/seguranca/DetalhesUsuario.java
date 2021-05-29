package com.orangetalents.mercadolivre.seguranca;

import com.orangetalents.mercadolivre.usuarios.Usuario;
import com.orangetalents.mercadolivre.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalhesUsuario implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByUsername(username);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Não foi possível encontrar usuário com login: " + username);
        }
        return new UsuarioLogadoDetails(usuario.get());
    }
}
