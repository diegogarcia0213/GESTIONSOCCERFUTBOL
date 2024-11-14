package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email); // Busca un usuario por su email

    List<Usuario> findByRol(Usuario.Rol rol); // Filtra usuarios por su rol


    List<Usuario> findByRol(String rol); // Nuevo método para encontrar usuarios por rol como String


    // Método para encontrar usuarios asociados a un partido específico
    @Query("SELECT u FROM Usuario u JOIN u.partidos p WHERE p.id = :partidoId")
    List<Usuario> findByPartidosId(@Param("partidoId") Long partidoId);
}

