package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.servicio.PartidosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PartidosControlador {

    @Autowired
    private PartidosServicio partidosServicio;

    // Rutas de "jugador"
    // Muestra la página para programar partidos (accesible por jugadores)
    @GetMapping("/partidos/programar")
    public String mostrarProgramarPartidos() {
        return "jugador/programarpartidos";
    }

    // Maneja el registro de un partido y redirige a la página principal de jugador con mensaje
    @PostMapping("/partidos/registrar")
    public String registrarPartido(@RequestParam("fecha") String fecha,
                                   @RequestParam("categoria") String categoria,
                                   HttpSession session,
                                   Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        partidosServicio.registrarPartido(usuarioId, LocalDate.parse(fecha), categoria);
        model.addAttribute("mensaje", "Partido agendado");
        return "redirect:/jugador/paginajugador?confirmacionPartido=true";
    }

    // Muestra la página de mis partidos con la lista de partidos (accesible por jugadores)
    @GetMapping("/partidos/mis")
    public String mostrarMisPartidos(HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId");
        List<Partidos> partidos = partidosServicio.listarPartidosPorUsuario(usuarioId);
        model.addAttribute("partidos", partidos);
        return "jugador/mispartidos";
    }

    // Maneja la eliminación de un partido por su ID (accesible por jugadores)
    @PostMapping("/partidos/eliminar/{id}")
    public String eliminarPartido(@PathVariable Long id) {
        partidosServicio.eliminarPartido(id);
        return "redirect:/partidos/mis";
    }

    // Rutas de "entrenador"
    // Filtra y muestra los partidos según la categoría seleccionada para el rol de entrenador
    @GetMapping("/entrenador/partidos/ver/{categoria}")
    public String mostrarPartidosPorCategoria(@PathVariable("categoria") String categoria, Model model) {
        List<Partidos> partidos = partidosServicio.listarPartidosPorCategoria(categoria);
        model.addAttribute("partidos", partidos);
        model.addAttribute("categoriaSeleccionada", categoria);
        return "entrenador/partidos";
    }
}

