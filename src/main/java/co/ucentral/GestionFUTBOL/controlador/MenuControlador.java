package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class MenuControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;
    // Página de inicio
    @GetMapping("/")
    public String mostrarInicio() {
        return "index"; // Redirige a index.html en la raíz de templates
    }

    // Página de login
    @GetMapping("/login")
    public String mostrarLogin(Model modelo) {
        return "login"; // Redirige a login.html en la raíz de templates
    }

    // Página de registro
    @GetMapping("/registro")
    public String mostrarRegistro(Model modelo) {
        return "registro"; // Redirige a registro.html en la raíz de templates
    }

    // Procesa el registro de un usuario
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model modelo) {
        usuarioServicio.registrarUsuario(usuario);
        modelo.addAttribute("mensaje", "Usuario registrado con éxito");
        return "registro"; // Redirige a login.html después de registrar
    }

    // Procesa el inicio de sesión y redirige según el rol
    @PostMapping("/iniciarSesion")
    public String iniciarSesion(@RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String rol,
                                Model modelo,
                                HttpSession session) {
        return usuarioServicio.validarCredenciales(email, password)
                .map(usuario -> {
                    if (usuario.getRol().name().equalsIgnoreCase(rol)) {
                        // Guarda el usuarioId en la sesión
                        session.setAttribute("usuarioId", usuario.getId());

                        if (rol.equalsIgnoreCase("JUGADOR")) {
                            return "redirect:/jugador/paginajugador"; // Redirige a la página del jugador en la carpeta 'jugador'
                        } else if (rol.equalsIgnoreCase("ENTRENADOR")) {
                            return "redirect:/entrenador/paginaentrenador"; // Redirige a la página del entrenador en la carpeta 'entrenador'
                        }
                    } else {
                        modelo.addAttribute("error", "Rol no válido.");
                    }
                    return "login"; // Vuelve a login si el rol es inválido
                })
                .orElseGet(() -> {
                    modelo.addAttribute("error", "Email o contraseña incorrectos.");
                    return "login"; // Vuelve a login si las credenciales son incorrectas
                });
    }

    // Muestra la página principal del jugador
    @GetMapping("/jugador/paginajugador")
    public String mostrarPaginaJugador() {
        return "jugador/paginajugador";  // Redirige a jugador/paginajugador.html
    }

    // Muestra la página principal del entrenador
    @GetMapping("/entrenador/paginaentrenador")
    public String mostrarPaginaEntrenador() {
        return "entrenador/paginaentrenador";  // Redirige a entrenador/paginaentrenador.html
    }
}
