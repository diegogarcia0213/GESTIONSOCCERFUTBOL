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

        @GetMapping("/programar")
        public String mostrarProgramarPartidos() {
            return "programarpartidos";
        }

        @PostMapping("/registrar")
        public String registrarPartido(@RequestParam("fecha") String fecha,
                                       @RequestParam("categoria") String categoria,
                                       Model model) {
            partidosServicio.registrarPartido(LocalDate.parse(fecha), categoria);
            model.addAttribute("mensaje", "Partido agendado");
            return "redirect:/paginajugador?confirmacionPartido=true";
        }

        @GetMapping("/mis")
        public String mostrarMisPartidos(Model model) {
            List<Partidos> partidos = partidosServicio.listarPartidos();
            model.addAttribute("partidos", partidos);
            return "mispartidos";
        }

        @PostMapping("/eliminar/{id}")
        public String eliminarPartido(@PathVariable Long id) {
            partidosServicio.eliminarPartido(id);
            return "redirect:/partidos/mis";
        }
    }
