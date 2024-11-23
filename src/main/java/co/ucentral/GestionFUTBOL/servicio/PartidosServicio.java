package co.ucentral.GestionFUTBOL.servicio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.PartidosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartidosServicio {

    @Autowired
    private PartidosRepositorio partidosRepositorio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Método para registrar un partido
    public void registrarPartido(Long usuarioId, LocalDate fecha, String categoria) {
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(usuarioId);
        if (usuario != null) {
            Partidos partido = new Partidos();
            partido.setUsuario(usuario);
            partido.setFecha(fecha);
            partido.setCategoria(categoria);
            partidosRepositorio.save(partido);
        }
    }

    // Método para listar partidos de un usuario específico
    public List<Partidos> listarPartidosPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(usuarioId);
        return partidosRepositorio.findAllByUsuarioOrderByFechaDesc(usuario);
    }

    // Método para eliminar un partido por su ID
    public void eliminarPartido(Long id) {
        partidosRepositorio.deleteById(id);
    }


    // Método para obtener los partidos de una categoría específica
    public List<Partidos> listarPartidosPorCategoria(String categoria) {
        return partidosRepositorio.findByCategoria(categoria);
    }


}
