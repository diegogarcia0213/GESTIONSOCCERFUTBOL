package co.ucentral.GestionFUTBOL.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/entrenador")
public class RegistrarPagosControlador {

    // Muestra la lista de jugadores
    @GetMapping("/lista")
    public String mostrarListaJugadores() {
        return "entrenador/listajugadores";
    }

    // Muestra la página para registrar pagos
    @GetMapping("/pago")
    public String mostrarRegistrarPagos() {
        return "entrenador/registrarpagos";
    }



}


