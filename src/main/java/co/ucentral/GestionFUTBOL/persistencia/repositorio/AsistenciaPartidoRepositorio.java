package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.AsistenciaPartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AsistenciaPartidoRepositorio extends JpaRepository<AsistenciaPartido, Long> {

    List<AsistenciaPartido> findByPartidoId(Long partidoId);

    AsistenciaPartido findByPartidoIdAndJugadorId(Long partidoId, Long jugadorId);
}
