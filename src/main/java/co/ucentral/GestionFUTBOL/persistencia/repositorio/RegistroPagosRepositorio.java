package co.ucentral.GestionFUTBOL.persistencia.repositorio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.RegistroPagos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroPagosRepositorio extends JpaRepository<RegistroPagos, Long> {
    void deleteByUsuario(Usuario usuario); // Método para eliminar registro de pagos por usuario
}
