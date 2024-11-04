package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.servicio.PartidosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/partidos")
public class PartidosControlador {

    @Autowired
    private PartidosServicio partidosServicio;

    // Muestra la página para programar partidos
    @GetMapping("/programar")
    public String mostrarProgramarPartidos() {
        return "jugador/programarpartidos"; // Apunta a jugador/programarpartidos.html
    }

    // Maneja el registro de un partido y redirige a la página principal de jugador con mensaje
    @PostMapping("/registrar")
    public String registrarPartido(@RequestParam("fecha") String fecha,
                                   @RequestParam("categoria") String categoria,
                                   Model model) {
        partidosServicio.registrarPartido(LocalDate.parse(fecha), categoria);
        model.addAttribute("mensaje", "Partido agendado");
        return "redirect:/jugador/paginajugador?confirmacionPartido=true"; // Redirige a jugador/paginajugador.html con confirmación
    }

    // Muestra la página de mis partidos con la lista de partidos
    @GetMapping("/mis")
    public String mostrarMisPartidos(Model model) {
        List<Partidos> partidos = partidosServicio.listarPartidos();
        model.addAttribute("partidos", partidos);
        return "jugador/mispartidos"; // Apunta a jugador/mispartidos.html
    }

    // Maneja la eliminación de un partido por su ID
    @PostMapping("/eliminar/{id}")
    public String eliminarPartido(@PathVariable Long id) {
        partidosServicio.eliminarPartido(id);
        return "redirect:/partidos/mis";
    }
}
