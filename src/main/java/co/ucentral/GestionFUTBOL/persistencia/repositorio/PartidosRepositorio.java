package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PartidosRepositorio extends JpaRepository<Partidos, Long> {

    List<Partidos> findAllByUsuarioIdOrderByFechaDesc(Long usuarioId);

    List<Partidos> findByCategoria(String categoria);
}
