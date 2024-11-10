package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Entrenamientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaEntrenamientosRepositorio extends JpaRepository<Entrenamientos, Long> {

    @Query("SELECT e FROM Entrenamientos e JOIN Usuario u ON e.usuarioId = u.id " +
            "WHERE e.categoria = :categoria AND u.rol = 'JUGADOR'")
    List<Entrenamientos> findEntrenamientosByCategoria(@Param("categoria") String categoria);
}
