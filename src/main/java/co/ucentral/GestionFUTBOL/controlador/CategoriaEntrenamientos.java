package co.ucentral.GestionFUTBOL.controlador;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entrenador")
public class CategoriaEntrenamientos {
    // Muestra la página de entrenamientos para niños
    @GetMapping("/entrenamientos/ver/ninos")
    public String mostrarEntrenamientosNinos() {
        return "entrenador/entrenamientosniños";
    }

    // Muestra la página de entrenamientos para adolescentes
    @GetMapping("/entrenamientos/ver/adolecentes")
    public String mostrarEntrenamientosAdolescentes() {
        return "entrenador/entrenamientosadolecentes";
    }

    // Muestra la página de entrenamientos para adultos
    @GetMapping("/entrenamientos/ver/adultos")
    public String mostrarEntrenamientosAdultos() {
        return "entrenador/entrenamientosadultos";
    }
}
