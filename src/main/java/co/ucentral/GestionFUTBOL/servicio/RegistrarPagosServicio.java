package co.ucentral.GestionFUTBOL.servicio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.RegistrarPagosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrarPagosServicio {

    @Autowired
    private RegistrarPagosRepositorio registrarPagosRepositorio;

    public List<Usuario> obtenerJugadores() {
        return registrarPagosRepositorio.findByRol("JUGADOR"); // Busca jugadores
    }

    public void registrarPago(Long jugadorId, int monto) {
        Usuario jugador = registrarPagosRepositorio.findById(jugadorId).orElse(null);
        if (jugador != null) {
            // lógica para actualizar estado de suscripción a activo y modo de suscripción a verde
        }
    }
}
