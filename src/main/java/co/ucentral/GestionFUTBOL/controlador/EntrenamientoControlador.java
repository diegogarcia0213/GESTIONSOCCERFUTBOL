package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Entrenamientos;
import co.ucentral.GestionFUTBOL.servicio.EntrenamientoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

@Controller
public class EntrenamientoControlador {

    @Autowired
    private EntrenamientoServicio entrenamientoServicio;

    // Rutas de "jugador"
    // Muestra la página para programar entrenamientos
    @GetMapping("/entrenamientos/programar")
    public String mostrarProgramarEntrenamientos() {
        return "jugador/programarentrenamientos";
    }

    // Maneja el registro de un entrenamiento y redirige a la página principal de jugador con mensaje
    @PostMapping("/entrenamientos/registrar")
    public String registrarEntrenamiento(@RequestParam("usuarioId") Long usuarioId,
                                         @RequestParam("fecha") String fecha,
                                         @RequestParam("categoria") String categoria,
                                         Model model) {
        entrenamientoServicio.registrarEntrenamiento(usuarioId, LocalDate.parse(fecha), categoria);
        model.addAttribute("mensaje", "Entrenamiento agendado");
        return "redirect:/jugador/paginajugador?confirmacion=true";
    }

    // Muestra la página de mis entrenamientos con la lista de entrenamientos
    @GetMapping("/entrenamientos/mis")
    public String mostrarMisEntrenamientos(HttpSession session, Model model) {
        Long usuarioId = (Long) session.getAttribute("usuarioId"); // Obtiene el usuarioId desde la sesión
        List<Entrenamientos> entrenamientos = entrenamientoServicio.listarEntrenamientosPorUsuario(usuarioId);
        model.addAttribute("entrenamientos", entrenamientos);
        return "jugador/misentrenamientos";
    }

    // Maneja la eliminación de un entrenamiento por su ID
    @PostMapping("/entrenamientos/eliminar/{id}")
    public String eliminarEntrenamiento(@PathVariable Long id) {
        entrenamientoServicio.eliminarEntrenamiento(id);
        return "redirect:/entrenamientos/mis";
    }

    // Rutas de "entrenador"
    // Filtra y muestra los entrenamientos según la categoría seleccionada para el rol de entrenador
    @GetMapping("/entrenador/entrenamientos/ver/{categoria}")
    public String mostrarEntrenamientosPorCategoria(@PathVariable("categoria") String categoria, Model model) {
        List<Entrenamientos> entrenamientos = entrenamientoServicio.listarEntrenamientosPorCategoria(categoria);
        model.addAttribute("entrenamientos", entrenamientos);
        model.addAttribute("categoriaSeleccionada", categoria);
        return "entrenador/entrenamientos";
    }
}

