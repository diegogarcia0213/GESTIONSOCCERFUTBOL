package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Entrenamientos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EntrenamientoRepositorio extends JpaRepository<Entrenamientos, Long> {
    List<Entrenamientos> findAllByUsuarioIdOrderByFechaDesc(Long usuarioId);
}
