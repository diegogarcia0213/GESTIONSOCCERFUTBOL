package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistrarPagosRepositorio extends JpaRepository<Usuario, Long> {
    List<Usuario> findByRol(String rol);
}
