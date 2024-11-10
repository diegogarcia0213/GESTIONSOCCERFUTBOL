package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email); // Busca un usuario por su email

    List<Usuario> findByRol(Rol rol); // Filtra usuarios por su rol

    List<Usuario> findByRol(String rol); // Nuevo método para encontrar usuarios por rol como String

    // Consulta personalizada para encontrar usuarios que hayan seleccionado una fecha específica en una categoría
    @Query("SELECT u FROM Usuario u JOIN Entrenamientos e ON u.id = e.usuarioId " +
            "WHERE e.fecha = :fecha AND e.categoria = :categoria AND u.rol = 'JUGADOR'")
    List<Usuario> findUsuariosByFechaAndCategoria(@Param("fecha") LocalDate fecha, @Param("categoria") String categoria);
}

