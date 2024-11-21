package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.persistencia.entidades.AsistenciaPartido;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.servicio.AsistenciaPartidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/entrenador")
public class AsistenciaPartidoControlador {

    @Autowired
    private AsistenciaPartidoServicio asistenciaPartidoServicio;

    // Mostrar todos los partidos registrados
    @GetMapping("/asistenciapartidos")
    public String mostrarAsistenciaPartidos(Model model) {
        List<Partidos> partidos = asistenciaPartidoServicio.obtenerTodosLosPartidos();
        model.addAttribute("partidos", partidos);
        return "entrenador/asistenciapartidos";
    }

    // Mostrar los jugadores inscritos en un partido específico
    @GetMapping("/fechaasistencia")
    public String mostrarFechaAsistencia(@RequestParam("selectedPartido") Long partidoId, Model model) {
        Partidos partido = asistenciaPartidoServicio.obtenerPartidoPorId(partidoId);
        if (partido == null) {
            model.addAttribute("error", "El partido seleccionado no existe.");
            return "entrenador/asistenciapartidos";
        }

        List<Usuario> jugadores = asistenciaPartidoServicio.obtenerJugadoresPorPartido(partidoId);
        List<AsistenciaPartido> listaAsistencias = asistenciaPartidoServicio.obtenerAsistenciasPorPartido(partidoId);

        model.addAttribute("partido", partido);
        model.addAttribute("jugadores", jugadores);
        model.addAttribute("listaAsistencias", listaAsistencias);
        return "entrenador/fechaasistencia";
    }

    // Guardar o actualizar las asistencias
    @PostMapping("/guardarAsistencia")
    public String guardarAsistencia(@RequestParam Long partidoId,
                                    @RequestParam Map<String, String> asistencias,
                                    Model model) {
        try {
            // Filtrar claves válidas y procesar las asistencias
            Map<Long, String> asistenciasMap = asistencias.entrySet().stream()
                    .filter(entry -> entry.getKey().startsWith("asistencias[") && entry.getKey().endsWith("]"))
                    .collect(Collectors.toMap(
                            entry -> {
                                String key = entry.getKey();
                                // Extraer el ID del jugador de la clave (ejemplo: asistencias[2])
                                return Long.parseLong(key.substring(key.indexOf('[') + 1, key.indexOf(']')));
                            },
                            Map.Entry::getValue
                    ));

            // Guardar las asistencias en la base de datos
            asistenciaPartidoServicio.guardarAsistencias(asistenciasMap, partidoId);

            // Obtener la lista actualizada de asistencias
            List<AsistenciaPartido> listaAsistencias = asistenciaPartidoServicio.obtenerAsistenciasPorPartido(partidoId);
            model.addAttribute("mensaje", "Asistencias guardadas correctamente.");
            model.addAttribute("listaAsistencias", listaAsistencias);
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar las asistencias: " + e.getMessage());
            e.printStackTrace();
        }

        // Recargar la página con los datos actualizados
        return mostrarFechaAsistencia(partidoId, model);
    }
}

