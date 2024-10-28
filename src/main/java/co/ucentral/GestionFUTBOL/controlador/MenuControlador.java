package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MenuControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

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

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model modelo) {
        usuarioServicio.registrarUsuario(usuario);
        modelo.addAttribute("mensaje", "Usuario registrado con éxito");
        return "login"; // Redirige a login.html después de registrar
    }

    @PostMapping("/iniciarSesion")
    public String iniciarSesion(@RequestParam String email, @RequestParam String password, Model modelo) {
        return usuarioServicio.validarCredenciales(email, password)
                .map(usuario -> "redirect:/") // Redirige a la página principal si las credenciales son correctas
                .orElseGet(() -> {
                    modelo.addAttribute("error", "Email o contraseña incorrectos");
                    return "login"; // Vuelve a login.html si hay error
                });
    }
}
