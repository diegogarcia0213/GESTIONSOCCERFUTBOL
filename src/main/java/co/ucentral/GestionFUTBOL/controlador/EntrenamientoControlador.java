package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Entrenamientos;
import co.ucentral.GestionFUTBOL.servicio.EntrenamientoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/entrenamientos")
public class EntrenamientoControlador {

    @Autowired
    private EntrenamientoServicio entrenamientoServicio;

    // Muestra la página para programar entrenamientos
    @GetMapping("/programar")
    public String mostrarProgramarEntrenamientos() {
        return "programarentrenamientos";
    }

    // Maneja el registro de un entrenamiento y redirige a la página principal de jugador con mensaje
    @PostMapping("/registrar")
    public String registrarEntrenamiento(@RequestParam("fecha") String fecha,
                                         @RequestParam("categoria") String categoria,
                                         Model model) {
        entrenamientoServicio.registrarEntrenamiento(LocalDate.parse(fecha), categoria);
        model.addAttribute("mensaje", "Entrenamiento agendado");
        return "redirect:/paginajugador?confirmacion=true"; // Redirige a paginajugador.html con un mensaje de confirmación
    }

    // Muestra la página de mis entrenamientos con la lista de entrenamientos
    @GetMapping("/mis")
    public String mostrarMisEntrenamientos(Model model) {
        List<Entrenamientos> entrenamientos = entrenamientoServicio.listarEntrenamientos();
        model.addAttribute("entrenamientos", entrenamientos);
        return "misentrenamientos";
    }

    // Maneja la eliminación de un entrenamiento por su ID
    @PostMapping("/eliminar/{id}")
    public String eliminarEntrenamiento(@PathVariable Long id) {
        entrenamientoServicio.eliminarEntrenamiento(id);
        return "redirect:/entrenamientos/mis";
    }
}
