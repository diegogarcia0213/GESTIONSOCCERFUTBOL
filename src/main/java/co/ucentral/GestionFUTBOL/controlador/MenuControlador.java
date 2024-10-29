package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.servicio.UsuarioServicio;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
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
    public String iniciarSesion(@RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String rol,
                                Model modelo) {
        return usuarioServicio.validarCredenciales(email, password)
                .map(usuario -> {
                    if (rol.equalsIgnoreCase("jugador")) {
                        return "redirect:/paginajugador"; // Redirige a la página del jugador
                    } else if (rol.equalsIgnoreCase("entrenador")) {
                        return "redirect:/paginaentrenador"; // Redirige a la página del entrenador
                    } else {
                        modelo.addAttribute("error", "Rol no válido.");
                        return "login"; // Vuelve a login si el rol es inválido
                    }
                })
                .orElseGet(() -> {
                    modelo.addAttribute("error", "Email o contraseña incorrectos.");
                    return "login"; // Vuelve a login si las credenciales son incorrectas
                });
    }

    @GetMapping("/paginajugador")
    public String mostrarPaginaJugador() {
        return "paginajugador";  // Redirige a paginajugador.html
    }

    @GetMapping("/paginaentrenador")
    public String mostrarPaginaEntrenador() {
        return "paginaentrenador";  // Redirige a paginaentrenador.html
    }
}


