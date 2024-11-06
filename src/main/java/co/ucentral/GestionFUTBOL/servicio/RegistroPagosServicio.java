package co.ucentral.GestionFUTBOL.servicio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.RegistroPagos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.RegistroPagosRepositorio;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroPagosServicio {

    @Autowired
    private RegistroPagosRepositorio registroPagosRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public boolean registrarPago(Long usuarioId, int monto) {
        if (monto == 50000) {
            try {
                Usuario usuario = usuarioRepositorio.findById(usuarioId).orElse(null);
                if (usuario != null) {
                    RegistroPagos pago = new RegistroPagos();
                    pago.setUsuario(usuario);
                    pago.setMonto(monto);
                    pago.setEstadoSuscripcion("Activo");
                    pago.setModoSuscripcion("Green"); // Indicador de pago (verde)
                    registroPagosRepositorio.save(pago);
                    usuario.setEstadoSuscripcion("Activo"); // Actualiza el estado en Usuario
                    usuarioRepositorio.save(usuario); // Guarda el cambio en Usuario
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace(); // Imprime la excepción en los logs para depuración
            }
        }
        return false;
    }
}
