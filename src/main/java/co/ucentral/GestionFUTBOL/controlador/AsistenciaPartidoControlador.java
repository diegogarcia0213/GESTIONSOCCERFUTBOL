package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.servicio.AsistenciaPartidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/entrenador")
public class AsistenciaPartidoControlador {

    @Autowired
    private AsistenciaPartidoServicio asistenciaPartidoServicio;

    @GetMapping("/asistenciapartidos")
    public String mostrarAsistenciaPartidos(Model model) {
        List<Partidos> partidos = asistenciaPartidoServicio.obtenerTodosLosPartidos();
        model.addAttribute("partidos", partidos);
        return "entrenador/asistenciapartidos";
    }

    @GetMapping("/fechaasistencia")
    public String mostrarFechaAsistencia(@RequestParam("selectedPartido") Long partidoId, Model model) {
        Partidos partido = asistenciaPartidoServicio.obtenerPartidoPorId(partidoId);
        List<Usuario> jugadores = asistenciaPartidoServicio.obtenerJugadoresPorPartido(partidoId);
        model.addAttribute("partido", partido);
        model.addAttribute("jugadores", jugadores);
        return "entrenador/fechaasistencia";
    }

    @PostMapping("/guardarAsistencia")
    public ResponseEntity<String> guardarAsistencia(@RequestParam Long partidoId,
                                                    @RequestParam Map<Long, Integer> asistencias) {
        asistenciaPartidoServicio.guardarAsistencias(asistencias, partidoId);
        return ResponseEntity.ok("Asistencia guardada con éxito");
    }
}


