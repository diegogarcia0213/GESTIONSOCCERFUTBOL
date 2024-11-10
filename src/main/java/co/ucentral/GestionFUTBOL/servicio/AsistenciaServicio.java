package co.ucentral.GestionFUTBOL.servicio;

import co.ucentral.GestionFUTBOL.persistencia.repositorio.PartidosRepositorio;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import java.util.List;

@Service
public class AsistenciaServicio {

    @Autowired
    private PartidosRepositorio partidoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public List<Partidos> obtenerTodosLosPartidos() {
        return partidoRepositorio.findAll();
    }

    public List<Usuario> obtenerJugadoresPorPartido(Long partidoId) {
        // Aquí podrías hacer una lógica para obtener solo jugadores asociados, si tienes relación en tu modelo
        return usuarioRepositorio.findAll(); // O filtrar con un criterio específico
    }

    public void guardarAsistencia(List<Usuario> jugadores) {
        usuarioRepositorio.saveAll(jugadores); // Guarda los cambios en la asistencia
    }

    // Nuevo método para obtener todos los jugadores
    public List<Usuario> obtenerTodosLosJugadores() {
        return usuarioRepositorio.findAll(); // Recupera todos los usuarios de la base de datos
    }
}

