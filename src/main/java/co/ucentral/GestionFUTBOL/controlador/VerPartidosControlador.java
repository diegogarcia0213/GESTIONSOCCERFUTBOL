package co.ucentral.GestionFUTBOL.controlador;


import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.servicio.PartidosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@Controller
@RequestMapping("/entrenador")
public class VerPartidosControlador {

    @Autowired
    private PartidosServicio partidosServicio;

    // Muestra la página de ver partidos
    @GetMapping("/partidos/ver/adultos")
    public String mostrarVerPartidos(Model model) {
        List<Partidos> partidos = partidosServicio.listarPartidos();
        model.addAttribute("partidos", partidos);
        return "entrenador/partidosadultos";
    }
}
