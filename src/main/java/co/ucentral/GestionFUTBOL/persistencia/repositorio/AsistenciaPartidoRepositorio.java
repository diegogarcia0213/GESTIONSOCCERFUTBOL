package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.AsistenciaPartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsistenciaPartidoRepositorio extends JpaRepository<AsistenciaPartido, Long> {

    // Obtener todas las asistencias de un partido específico
    List<AsistenciaPartido> findByPartidoId(Long partidoId);

    // Buscar una asistencia específica por partido y jugador
    Optional<AsistenciaPartido> findByPartidoIdAndJugadorId(Long partidoId, Long jugadorId);
}

