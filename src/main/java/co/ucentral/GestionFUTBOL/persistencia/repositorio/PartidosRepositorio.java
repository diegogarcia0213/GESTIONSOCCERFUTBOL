package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PartidosRepositorio extends JpaRepository<Partidos, Long> {

    // Método actualizado para listar partidos de un usuario específico ordenados por fecha descendente
    List<Partidos> findAllByUsuarioOrderByFechaDesc(Usuario usuario);

    // Método para buscar partidos por categoría
    List<Partidos> findByCategoria(String categoria);
}

