package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Entrenamientos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidosRepositorio extends JpaRepository<Partidos, Long> {
    List<Entrenamientos> findAllByUsuarioIdOrderByFechaDesc(Long usuarioId);
    // Método para listar partidos de un usuario específico ordenados por fecha descendente
    List<Partidos> findAllByUsuarioOrderByFechaDesc(Usuario usuario);

    // Método para buscar partidos por categoría
    List<Partidos> findByCategoria(String categoria);
}


