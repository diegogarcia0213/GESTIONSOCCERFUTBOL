package co.ucentral.GestionFUTBOL.controlador;


import co.ucentral.GestionFUTBOL.servicio.AsistenciaPartidosServicio;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.servicio.PartidosServicio;
import co.ucentral.GestionFUTBOL.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

        import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/entrenador")
public class AsistenciaPartidosControlador {

    @Autowired
    private AsistenciaPartidosServicio asistenciaPartidosServicio;

    @Autowired
    private PartidosServicio partidosServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Muestra la página de asistencia a partidos
    @GetMapping("/asistenciapartidos")
    public String mostrarAsistenciaPartidos(Model model) {
        // Se obtienen todos los partidos registrados en la base de datos
        List<Partidos> partidos = partidosServicio.listarPartidos();
        model.addAttribute("partidos", partidos);
        return "entrenador/asistenciapartidos"; // Redirige a asistenciapartidos.html en la carpeta `entrenador`
    }

    // Muestra los jugadores para un partido específico
    @GetMapping("/asistenciapartidos/{partidoId}")
    public String mostrarJugadores(@PathVariable Long partidoId, Model model, HttpSession session) {
        // Obtiene el partido por el ID
        Partidos partido = partidosServicio.obtenerPartidoPorId(partidoId);

        // Se obtiene la lista de jugadores asociados con ese partido (solo jugadores inscritos)
        List<Usuario> jugadores = usuarioServicio.obtenerUsuariosPorRol("JUGADOR");

        // Se agrega el partido y los jugadores a la vista
        model.addAttribute("partido", partido);
        model.addAttribute("jugadores", jugadores);

        // Redirige a la página fechaasistencia.html
        return "entrenador/fechaasistencia";
    }

    // Guarda la asistencia para los jugadores seleccionados
    @PostMapping("/guardarAsistencia")
    public String guardarAsistencia(@RequestParam("jugadores") List<Long> jugadorIds,
                                    @RequestParam("asistencia") List<Boolean> asistencias,
                                    @RequestParam("partidoId") Long partidoId) {

        // Guarda la asistencia para cada jugador
        for (int i = 0; i < jugadorIds.size(); i++) {
            Usuario jugador = usuarioServicio.obtenerUsuarioPorId(jugadorIds.get(i));
            Boolean asistencia = asistencias.get(i);

            // Crea la entidad AsistenciaPartidos y la guarda
            asistenciaPartidosServicio.guardarAsistencia(jugador, partidoId, asistencia);
        }

        return "redirect:/entrenador/asistenciapartidos"; // Redirige a la página de asistencia a partidos
    }

    // Muestra la lista de jugadores para un partido y su estado de asistencia
    @GetMapping("/jugadores/asistencia")
    public String mostrarAsistenciaPagos(Model model) {
        // Obtener todos los jugadores
        List<Usuario> jugadores = usuarioServicio.obtenerTodosLosJugadores();
        model.addAttribute("jugadores", jugadores);
        return "entrenador/asistenciapartidos"; // Reutiliza la página asistenciapartidos.html
    }
}
