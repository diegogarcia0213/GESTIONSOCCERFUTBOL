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

    // Método para listar todos los partidos
    public List<Partidos> listarPartidos() {
        return partidosRepositorio.findAll();
    }

    // Método para obtener un partido por su ID
    public Partidos obtenerPartidoPorId(Long partidoId) {
        Optional<Partidos> partido = partidosRepositorio.findById(partidoId);
        return partido.orElse(null);
    }

    // Método para obtener los partidos de una categoría específica
    public List<Partidos> listarPartidosPorCategoria(String categoria) {
        return partidosRepositorio.findByCategoria(categoria);
    }

    // Método para obtener los partidos de la categoría "Adultos" asociados a usuarios con rol "Jugador"
    public List<Partidos> obtenerPartidosAdultosPorJugadores() {
        List<Partidos> partidosAdultos = partidosRepositorio.findByCategoria("Adultos");
        return partidosAdultos.stream()
                .filter(partido -> partido.getUsuario().getRol().name().equalsIgnoreCase("jugador"))
                .collect(Collectors.toList());
    }

    // Método para obtener los partidos de la categoría "Niños" asociados a usuarios con rol "Jugador"
    public List<Partidos> obtenerPartidosNinos() {
        List<Partidos> partidosNinos = partidosRepositorio.findByCategoria("Niños");
        System.out.println("Número total de partidos de categoría 'Niños': " + partidosNinos.size());

        List<Partidos> partidosFiltrados = partidosNinos.stream()
                .filter(partido -> {
                    boolean esJugador = partido.getUsuario().getRol().name().equalsIgnoreCase("jugador");
                    System.out.println("Partido ID: " + partido.getId() + ", Fecha: " + partido.getFecha() +
                            ", Usuario Rol: " + partido.getUsuario().getRol().name() +
                            ", Es Jugador: " + esJugador);
                    return esJugador;
                })
                .collect(Collectors.toList());

        System.out.println("Número de partidos de categoría 'Niños' con rol 'Jugador': " + partidosFiltrados.size());
        return partidosFiltrados;
    }
}
