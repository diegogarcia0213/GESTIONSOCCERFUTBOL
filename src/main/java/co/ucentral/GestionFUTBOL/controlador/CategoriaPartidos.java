package co.ucentral.GestionFUTBOL.controlador;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entrenador")
public class CategoriaPartidos {
    // Muestra la página de partidos para niños
    @GetMapping("/partidos/ver/ninos")
    public String mostrarPartidosNinos() {
        return "entrenador/partidosniños";
    }

    // Muestra la página de partidos para adolescentes
    @GetMapping("/partidos/ver/adolecentes")
    public String mostrarPartidosAdolescentes() {
        return "entrenador/partidosadolecentes";
    }

    // Muestra la página de partidos para adultos
    @GetMapping("/partidos/ver/adultos")
    public String mostrarPartidosAdultos() {
        return "entrenador/partidosadultos";
    }
}
