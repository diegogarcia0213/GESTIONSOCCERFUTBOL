package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaPartidosRepositorio extends JpaRepository<Partidos, Long> {
    // Método para obtener partidos de la categoría "adultos"
    List<Partidos> findByCategoria(String categoria);
}






