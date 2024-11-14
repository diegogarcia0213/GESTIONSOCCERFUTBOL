package co.ucentral.GestionFUTBOL.controlador;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/entrenador")
public class RegistrarPagosControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/pago")
    public String mostrarRegistrarPago(Model model) {
        List<Usuario> jugadores = usuarioRepositorio.findByRol(Usuario.Rol.JUGADOR);
        model.addAttribute("jugadores", jugadores);
        return "entrenador/registrarpagos";
    }

    @PostMapping("/pago")
    public String registrarPago(@RequestParam Long usuarioId, @RequestParam int monto, Model model) {
        if (monto == 50000) {
            Usuario usuario = usuarioRepositorio.findById(usuarioId).orElse(null);
            if (usuario != null) {
                usuario.setEstadoSuscripcion("Activo");
                usuarioRepositorio.save(usuario);
            }
            return "redirect:/entrenador/lista";
        } else {
            model.addAttribute("error", "El monto debe ser 50,000 para registrar el pago.");
            return "entrenador/registrarpagos";
        }
    }

    @GetMapping("/lista")
    public String listarJugadores(Model model) {
        List<Usuario> jugadores = usuarioRepositorio.findByRol(Usuario.Rol.JUGADOR);
        model.addAttribute("jugadores", jugadores);
        return "entrenador/listajugadores";
    }

    // Método para desactivar jugadores seleccionados actualizando solo el estado en Usuario
    @PostMapping("/eliminar-jugadores")
    public String eliminarJugadoresActivos(@RequestParam List<Long> jugadorIds) {
        for (Long id : jugadorIds) {
            Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
            if (usuario != null && "Activo".equals(usuario.getEstadoSuscripcion())) {
                usuario.setEstadoSuscripcion("Inactivo");  // Cambia el estado a inactivo
                usuarioRepositorio.save(usuario);  // Guarda el cambio en la base de datos
            }
        }
        return "redirect:/entrenador/lista"; // Redirige a lista de jugadores para ver los cambios
    }
}

