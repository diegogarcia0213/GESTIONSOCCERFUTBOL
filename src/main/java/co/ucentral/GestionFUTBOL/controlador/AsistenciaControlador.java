package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.servicio.AsistenciaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;

@Controller
public class AsistenciaControlador {

    @Autowired
    private AsistenciaServicio asistenciaServicio;

    @GetMapping("/paginaentrenador")
    public String mostrarPaginaEntrenador(Model model) {
        model.addAttribute("partidos", asistenciaServicio.obtenerTodosLosPartidos());
        return "paginaentrenador";
    }

    @GetMapping("/asistenciapartidos")
    public String mostrarAsistenciaPartidos(Model model) {
        model.addAttribute("partidos", asistenciaServicio.obtenerTodosLosPartidos());
        return "asistenciapartidos";
    }

    @GetMapping("/asistenciapartidos/{partidoId}")
    public String mostrarJugadores(@PathVariable Long partidoId, Model model) {
        List<Usuario> jugadores = asistenciaServicio.obtenerJugadoresPorPartido(partidoId);
        model.addAttribute("jugadores", jugadores);
        return "asistenciapartidos";
    }

    @PostMapping("/guardarAsistencia")
    public String guardarAsistencia(@RequestParam("jugadores") List<Usuario> jugadores) {
        asistenciaServicio.guardarAsistencia(jugadores);
        return "redirect:/asistenciapartidos";
    }

    // Nuevo método para manejar la asistencia de pagos y redirigir a `asistenciapartidos.html`
    @GetMapping("/entrenador/jugadores/asistencia")
    public String mostrarAsistenciaPagos(Model model) {
        model.addAttribute("jugadores", asistenciaServicio.obtenerTodosLosJugadores()); // Llama a un método que obtenga todos los jugadores
        return "asistenciapartidos"; // Reutiliza la página asistenciapartidos.html
    }
}
