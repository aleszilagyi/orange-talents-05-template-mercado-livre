package com.orangetalents.mercadolivre.compartilhado.credenciais;

import com.orangetalents.mercadolivre.usuarios.Usuario;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Component
public class BuscaUsuario {

    public Usuario retornaUsuario(EntityManager manager, String username) {
        Query queryUser = manager.createQuery("SELECT u from Usuario u where username = :username");
        queryUser.setParameter("username", username);
        Usuario result = (Usuario) queryUser.getSingleResult();
        return result;
    }

    public void usuariosDevemSerIguais(Usuario usuario, Usuario usuarioComparado) throws AuthenticationCredentialsNotFoundException {
        HttpHeaders header = new HttpHeaders();
        if (usuario != usuarioComparado) {
            throw new AuthenticationCredentialsNotFoundException("usuário inválido para realizar a operação");
        }
    }

    public void usuariosDevemSerDiferentes(Usuario usuario, Usuario usuarioComparado) throws AuthenticationCredentialsNotFoundException {
        HttpHeaders header = new HttpHeaders();
        if (usuario == usuarioComparado) {
            throw new AuthenticationCredentialsNotFoundException("usuário inválido para realizar a operação");
        }
    }
}
