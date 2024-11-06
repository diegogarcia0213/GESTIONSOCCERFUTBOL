package co.ucentral.GestionFUTBOL.servicio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.RegistroPagos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.RegistroPagosRepositorio;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroPagosServicio {

    @Autowired
    private RegistroPagosRepositorio registroPagosRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public boolean registrarPago(Long usuarioId, int monto) {
        if (monto == 50000) {
            try {
                Usuario usuario = usuarioRepositorio.findById(usuarioId).orElse(null);
                if (usuario != null) {
                    // Crear y guardar el registro de pago
                    RegistroPagos pago = new RegistroPagos();
                    pago.setUsuario(usuario);
                    pago.setMonto(monto);
                    registroPagosRepositorio.save(pago);

                    // Actualizar el estado de suscripción en Usuario
                    usuario.setEstadoSuscripcion("Activo");
                    usuarioRepositorio.save(usuario);

                    System.out.println("Registro de pago y actualización de usuario exitosos.");
                    return true;
                } else {
                    System.out.println("Usuario no encontrado con ID: " + usuarioId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Monto inválido para registrar el pago: " + monto);
        }
        return false;
    }
}
