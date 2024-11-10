package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.AsistenciaPartidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AsistenciaPartidosRepositorio extends JpaRepository<AsistenciaPartidos, Long> {
    List<AsistenciaPartidos> findByPartidoAndUsuario(Partidos partido, Usuario usuario);
}
