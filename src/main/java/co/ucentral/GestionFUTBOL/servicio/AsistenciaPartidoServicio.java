package co.ucentral.GestionFUTBOL.servicio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.AsistenciaPartido;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.AsistenciaPartidoRepositorio;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.PartidosRepositorio;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AsistenciaPartidoServicio {

    @Autowired
    private AsistenciaPartidoRepositorio asistenciaPartidoRepositorio;

    @Autowired
    private PartidosRepositorio partidoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // Obtener todos los partidos
    public List<Partidos> obtenerTodosLosPartidos() {
        return partidoRepositorio.findAll();
    }

    // Obtener partido por ID
    public Partidos obtenerPartidoPorId(Long partidoId) {
        return partidoRepositorio.findById(partidoId).orElse(null);
    }

    // Obtener jugadores asociados a un partido
    public List<Usuario> obtenerJugadoresPorPartido(Long partidoId) {
        // Busca los jugadores que están asociados a este partido
        return usuarioRepositorio.findByPartidosId(partidoId);
    }

    // Obtener asistencias de un partido
    public List<AsistenciaPartido> obtenerAsistenciasPorPartido(Long partidoId) {
        return asistenciaPartidoRepositorio.findByPartidoId(partidoId);
    }

    // Guardar asistencias (evitando duplicados)
    public void guardarAsistencias(Map<Long, String> asistencias, Long partidoId) {
        Partidos partido = obtenerPartidoPorId(partidoId);

        for (Map.Entry<Long, String> entry : asistencias.entrySet()) {
            Long jugadorId = entry.getKey();
            String estadoAsistencia = entry.getValue();

            Usuario jugador = usuarioRepositorio.findById(jugadorId).orElse(null);
            if (jugador != null && partido != null) {
                // Verificar si ya existe una asistencia para ese jugador y partido
                AsistenciaPartido asistenciaExistente = asistenciaPartidoRepositorio.findByPartidoIdAndJugadorId(partidoId, jugadorId);
                if (asistenciaExistente != null) {
                    // Actualizar asistencia existente
                    asistenciaExistente.setAsistencia(estadoAsistencia);
                    asistenciaPartidoRepositorio.save(asistenciaExistente);
                } else {
                    // Crear nueva asistencia si no existe
                    AsistenciaPartido nuevaAsistencia = new AsistenciaPartido();
                    nuevaAsistencia.setPartido(partido);
                    nuevaAsistencia.setJugador(jugador);
                    nuevaAsistencia.setAsistencia(estadoAsistencia);
                    asistenciaPartidoRepositorio.save(nuevaAsistencia);
                }
            }
        }
    }
}


