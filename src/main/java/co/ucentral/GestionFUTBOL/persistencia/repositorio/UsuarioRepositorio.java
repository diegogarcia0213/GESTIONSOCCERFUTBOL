package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email); // Busca un usuario por su email

    List<Usuario> findByRol(Rol rol); // Filtra usuarios por su rol
}
