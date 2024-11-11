package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.servicio.PartidosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/entrenador")
public class CategoriaPartidosControlador {

    private final PartidosServicio partidosServicio;

    @Autowired
    public CategoriaPartidosControlador(PartidosServicio partidosServicio) {
        this.partidosServicio = partidosServicio;
    }

    @GetMapping("/partidos/ver/adultos")
    public String mostrarPartidosAdultos(Model model) {
        List<Partidos> partidosAdultos = partidosServicio.obtenerPartidosAdultosPorJugadores();
        model.addAttribute("partidos", partidosAdultos);
        return "entrenador/partidosadultos";  // Nombre de la vista
    }
}




