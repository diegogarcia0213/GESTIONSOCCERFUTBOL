package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Entrenamientos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrenamientoRepositorio extends JpaRepository<Entrenamientos, Long> {

    // Método para listar entrenamientos de un usuario específico ordenados por fecha descendente
    List<Entrenamientos> findAllByUsuarioOrderByFechaDesc(Usuario usuario);

    // Método para buscar entrenamientos por categoría
    List<Entrenamientos> findByCategoria(String categoria);
}
