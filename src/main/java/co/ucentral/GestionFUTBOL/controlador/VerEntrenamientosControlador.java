package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Entrenamientos;
import co.ucentral.GestionFUTBOL.servicio.EntrenamientoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/entrenador")
public class VerEntrenamientosControlador {

    @Autowired
    private EntrenamientoServicio entrenamientoServicio;

    // Filtra y muestra los entrenamientos según la categoría seleccionada
    @GetMapping("/entrenamientos/ver/{categoria}")
    public String mostrarEntrenamientosPorCategoria(@PathVariable("categoria") String categoria, Model model) {
        List<Entrenamientos> entrenamientos = entrenamientoServicio.listarEntrenamientosPorCategoria(categoria);
        model.addAttribute("entrenamientos", entrenamientos);
        model.addAttribute("categoriaSeleccionada", categoria);
        return "entrenador/entrenamientos";
    }
}

