package com.orangetalents.mercadolivre.seguranca;

import com.orangetalents.mercadolivre.usuarios.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Component
public class DetalhesUsuario implements UserDetailsService {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query = manager.createQuery("SELECT u from Usuario u where username = :username");
        query.setParameter("username", username);
        Usuario usuario = (Usuario) query.getSingleResult();
        if (usuario == null) {
            throw new UsernameNotFoundException("Não foi possível encontrar usuário com login: " + username);
        }
        return new UsuarioLogadoDetails(usuario);
    }
}
