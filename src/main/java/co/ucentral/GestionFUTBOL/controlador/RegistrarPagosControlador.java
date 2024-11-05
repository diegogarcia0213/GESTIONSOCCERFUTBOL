package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.servicio.RegistrarPagosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/entrenador")
public class RegistrarPagosControlador {

    @Autowired
    private RegistrarPagosServicio registrarPagosServicio;

    // Muestra la lista de jugadores
    @GetMapping("/lista")
    public String mostrarListaJugadores(Model model) {
        List<Usuario> jugadores = registrarPagosServicio.obtenerJugadores();
        model.addAttribute("jugadores", jugadores);
        return "entrenador/listajugadores"; // Redirige a entrenador/listajugadores.html
    }

    // Muestra la página para registrar pagos
    @GetMapping("/pago")
    public String mostrarRegistrarPagos(Model model) {
        List<Usuario> jugadores = registrarPagosServicio.obtenerJugadores();
        model.addAttribute("jugadores", jugadores);
        return "entrenador/registrarpagos"; // Redirige a entrenador/registrarpagos.html
    }

    // Procesa el pago para un jugador específico
    @PostMapping("/pago")
    public String procesarPago(@RequestParam("jugadorId") Long jugadorId, @RequestParam("monto") int monto) {
        registrarPagosServicio.registrarPago(jugadorId, monto);
        return "redirect:/entrenador/lista"; // Redirige a la lista de jugadores después de registrar el pago
    }

    // Muestra la página de asistencia a partidos
    @GetMapping("/asistenciapartidos")
    public String mostrarAsistenciaPartidos() {
        return "entrenador/asistenciapartidos"; // Redirige a entrenador/asistenciapartidos.html
    }
}
