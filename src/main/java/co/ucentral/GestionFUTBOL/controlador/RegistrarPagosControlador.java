package co.ucentral.GestionFUTBOL.controlador;


import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Rol;
import co.ucentral.GestionFUTBOL.servicio.RegistroPagosServicio;
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
    private RegistroPagosServicio registroPagosServicio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/pago")
    public String mostrarRegistrarPago(Model model) {
        List<Usuario> jugadores = usuarioRepositorio.findByRol(Rol.JUGADOR);
        model.addAttribute("jugadores", jugadores);
        return "entrenador/registrarpagos";
    }

    @PostMapping("/pago")
    public String registrarPago(@RequestParam Long usuarioId, @RequestParam int monto, Model model) {
        boolean exito = registroPagosServicio.registrarPago(usuarioId, monto);
        if (exito) {
            model.addAttribute("success", "Pago registrado con éxito.");
        } else {
            model.addAttribute("error", "El monto debe ser 50,000 para registrar el pago.");
        }
        return "registrarpagos";
    }

    @GetMapping("/lista")
    public String listarJugadores(Model model) {
        List<Usuario> jugadores = usuarioRepositorio.findByRol(Rol.JUGADOR);
        model.addAttribute("jugadores", jugadores);
        return "entrenador/listajugadores";
    }
}
