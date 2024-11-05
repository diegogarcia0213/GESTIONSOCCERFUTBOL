package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.servicio.AsistenciaServicio;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/entrenador")
public class AsistenciaControlador {

    @Autowired
    private AsistenciaServicio asistenciaServicio;

    // Muestra la página de asistencia a partidos
    @GetMapping("/asistenciapartidos")
    public String mostrarAsistenciaPartidos(Model model) {
        model.addAttribute("partidos", asistenciaServicio.obtenerTodosLosPartidos());
        return "entrenador/asistenciapartidos"; // Redirige a asistenciapartidos.html en la carpeta `entrenador`
    }

    // Muestra los jugadores para un partido específico
    @GetMapping("/asistenciapartidos/{partidoId}")
    public String mostrarJugadores(@PathVariable Long partidoId, Model model) {
        List<Usuario> jugadores = asistenciaServicio.obtenerJugadoresPorPartido(partidoId);
        model.addAttribute("jugadores", jugadores);
        return "entrenador/asistenciapartidos"; // Redirige a asistenciapartidos.html en la carpeta `entrenador`
    }

    // Guarda la asistencia para los jugadores seleccionados
    @PostMapping("/guardarAsistencia")
    public String guardarAsistencia(@RequestParam("jugadores") List<Usuario> jugadores) {
        asistenciaServicio.guardarAsistencia(jugadores);
        return "redirect:/entrenador/asistenciapartidos"; // Redirige a la página de asistencia a partidos después de guardar
    }

    // Muestra la asistencia de pagos para los jugadores
    @GetMapping("/jugadores/asistencia")
    public String mostrarAsistenciaPagos(Model model) {
        model.addAttribute("jugadores", asistenciaServicio.obtenerTodosLosJugadores()); // Llama al servicio para obtener todos los jugadores
        return "entrenador/asistenciapartidos"; // Reutiliza la página asistenciapartidos.html en la carpeta `entrenador`
    }
}

