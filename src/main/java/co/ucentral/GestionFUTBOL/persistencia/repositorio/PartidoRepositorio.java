package co.ucentral.GestionFUTBOL.persistencia.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import java.util.List;

public interface PartidoRepositorio extends JpaRepository<Partidos, Long> {
    List<Partidos> findByCategoria(String categoria);
}