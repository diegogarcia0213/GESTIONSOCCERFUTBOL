package co.ucentral.GestionFUTBOL.controlador;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuControlador {

    @GetMapping("/")
    public String mostrarInicio() {
        return "index"; // Redirige a index.html
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Redirige a login.html
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro"; // Redirige a registro.html
    }
}
