package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Entrenamientos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.servicio.CategoriaEntrenamientosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/entrenador")
public class CategoriaEntrenamientosControlador {

    @Autowired
    private CategoriaEntrenamientosServicio servicio;

    // Muestra la página de entrenamientos para adolescentes (adolescentes) con el diseño específico
    @GetMapping("/entrenamientos/ver/adolescentes")
    public String mostrarEntrenamientosAdolescentes(Model model) {
        List<Entrenamientos> entrenamientos = servicio.obtenerEntrenamientosPorCategoriaAdolescentes();
        model.addAttribute("entrenamientos", entrenamientos);
        return "entrenador/entrenamientosadolescentes";
    }

    // Muestra los jugadores que seleccionaron una fecha específica en entrenamientos de niños
    @GetMapping("/entrenamientos/mostrar/ninos")
    public String mostrarJugadoresNinos(@RequestParam("fecha") LocalDate fecha, Model model) {
        List<Usuario> jugadores = servicio.obtenerUsuariosPorFechaYCategoria(fecha, "niños");
        model.addAttribute("jugadores", jugadores);
        return "entrenador/mostrarniños";
    }

    // Muestra los jugadores que seleccionaron una fecha específica en entrenamientos de adolescentes
    @GetMapping("/entrenamientos/mostrar/adolescentes")
    public String mostrarJugadoresAdolescentes(@RequestParam("fecha") LocalDate fecha, Model model) {
        List<Usuario> jugadores = servicio.obtenerUsuariosPorFechaYCategoria(fecha, "adolescentes");
        model.addAttribute("jugadores", jugadores);
        return "entrenador/mostraradolescentes";
    }

    // Muestra los jugadores que seleccionaron una fecha específica en entrenamientos de adultos
    @GetMapping("/entrenamientos/mostrar/adultos")
    public String mostrarJugadoresAdultos(@RequestParam("fecha") LocalDate fecha, Model model) {
        List<Usuario> jugadores = servicio.obtenerUsuariosPorFechaYCategoria(fecha, "adultos");
        model.addAttribute("jugadores", jugadores);
        return "entrenador/mostraradultos";
    }
}
