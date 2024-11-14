package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.AsistenciaPartido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciaPartidoRepositorio extends JpaRepository<AsistenciaPartido, Long> {
    List<AsistenciaPartido> findByPartidoId(Long partidoId);
}
