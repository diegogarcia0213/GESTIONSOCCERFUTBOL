package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.servicio.PartidosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/entrenador")
public class VerPartidosControlador {

    @Autowired
    private PartidosServicio partidosServicio;

    // Filtra y muestra los partidos según la categoría seleccionada
    @GetMapping("/partidos/ver/{categoria}")
    public String mostrarPartidosPorCategoria(@PathVariable("categoria") String categoria, Model model) {
        List<Partidos> partidos = partidosServicio.listarPartidosPorCategoria(categoria);
        model.addAttribute("partidos", partidos);
        model.addAttribute("categoriaSeleccionada", categoria);
        return "entrenador/partidos";
    }
}
