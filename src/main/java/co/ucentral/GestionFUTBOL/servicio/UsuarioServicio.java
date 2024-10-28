package co.ucentral.GestionFUTBOL.servicio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // Método para registrar un usuario
    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario); // Guarda el usuario en la base de datos
    }

    public Optional<Usuario> validarCredenciales(String email, String password) {
        Optional<Usuario> usuario = usuarioRepositorio.findByEmail(email);
        if (usuario.isPresent() && usuario.get().getPassword().equals(password)) {
            return usuario;
        }
        return Optional.empty();
    }
}
