package co.ucentral.GestionFUTBOL.persistencia.repositorio;



import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerPartidosRepositorio extends JpaRepository<Partidos, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
