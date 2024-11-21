package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    // Busca un usuario por su email
    Optional<Usuario> findByEmail(String email);

    // Filtra usuarios por su rol
    List<Usuario> findByRol(Usuario.Rol rol);

    // Nuevo método para encontrar usuarios por rol como String
    List<Usuario> findByRol(String rol);

    // Método para encontrar usuarios asociados a un partido específico
    @Query("SELECT u FROM Usuario u JOIN u.partidos p WHERE p.id = :partidoId")
    List<Usuario> findByPartidosId(@Param("partidoId") Long partidoId);

}


