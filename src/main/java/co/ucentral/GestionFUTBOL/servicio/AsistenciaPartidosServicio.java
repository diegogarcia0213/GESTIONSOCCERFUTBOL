package co.ucentral.GestionFUTBOL.servicio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.AsistenciaPartidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.AsistenciaPartidosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsistenciaPartidosServicio {

    @Autowired
    private AsistenciaPartidosRepositorio asistenciaPartidosRepositorio;

    @Autowired
    private PartidosServicio partidosServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Método para guardar la asistencia de un jugador en un partido específico
    public void guardarAsistencia(Long jugadorId, Long partidoId, Boolean asistencia) {
        Usuario jugador = usuarioServicio.obtenerUsuarioPorId(jugadorId);
        Partidos partido = partidosServicio.obtenerPartidoPorId(partidoId);
        if (jugador != null && partido != null) {
            AsistenciaPartidos asistenciaPartido = new AsistenciaPartidos();
            asistenciaPartido.setUsuario(jugador);
            asistenciaPartido.setPartido(partido);
            asistenciaPartido.setAsistencia(asistencia);
            asistenciaPartidosRepositorio.save(asistenciaPartido);
        }
    }

    // Método para obtener todos los jugadores inscritos en un partido específico
    public List<AsistenciaPartidos> obtenerAsistenciasPorPartido(Long partidoId) {
        Partidos partido = partidosServicio.obtenerPartidoPorId(partidoId);
        return partido != null ? asistenciaPartidosRepositorio.findByPartido(partido) : List.of();
    }
}
