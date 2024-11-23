package co.ucentral.GestionFUTBOL.servicio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Entrenamientos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.EntrenamientoRepositorio;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EntrenamientoServicio {

    @Autowired
    private EntrenamientoRepositorio entrenamientoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // Método para registrar un entrenamiento con usuarioId
    public Entrenamientos registrarEntrenamiento(Long usuarioId, LocalDate fecha, String categoria) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId).orElse(null);
        if (usuario != null) {
            Entrenamientos entrenamiento = new Entrenamientos();
            entrenamiento.setUsuario(usuario);
            entrenamiento.setFecha(fecha);
            entrenamiento.setCategoria(categoria);
            return entrenamientoRepositorio.save(entrenamiento);
        }
        return null; // Devuelve null si el usuario no existe
    }

    // Método para listar entrenamientos de un usuario específico
    public List<Entrenamientos> listarEntrenamientosPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepositorio.findById(usuarioId).orElse(null);
        if (usuario != null) {
            return entrenamientoRepositorio.findAllByUsuarioOrderByFechaDesc(usuario);
        }
        return List.of(); // Devuelve una lista vacía si el usuario no existe
    }

    // Método para eliminar un entrenamiento por ID
    public void eliminarEntrenamiento(Long id) {
        if (entrenamientoRepositorio.existsById(id)) {
            entrenamientoRepositorio.deleteById(id);
        }
    }


    // Método para obtener los entrenamientos de una categoría específica
    public List<Entrenamientos> listarEntrenamientosPorCategoria(String categoria) {
        return entrenamientoRepositorio.findByCategoria(categoria);
    }
}
