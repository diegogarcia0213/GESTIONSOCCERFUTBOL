package co.ucentral.GestionFUTBOL.persistencia.repositorio;


import co.ucentral.GestionFUTBOL.persistencia.entidades.RegistroPagos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroPagosRepositorio extends JpaRepository<RegistroPagos, Long> {
}
