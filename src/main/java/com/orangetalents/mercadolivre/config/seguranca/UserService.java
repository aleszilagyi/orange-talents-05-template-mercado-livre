package com.orangetalents.mercadolivre.config.seguranca;

import com.orangetalents.mercadolivre.usuarios.Usuario;
import com.orangetalents.mercadolivre.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserService userService;
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
