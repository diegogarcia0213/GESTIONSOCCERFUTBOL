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
import java.util.Optional;

@Service
public class AsistenciaPartidoServicio {

    @Autowired
    private AsistenciaPartidoRepositorio asistenciaPartidoRepositorio;

    @Autowired
    private PartidosRepositorio partidoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public List<Partidos> obtenerTodosLosPartidos() {
        return partidoRepositorio.findAll();
    }

    public Partidos obtenerPartidoPorId(Long partidoId) {
        return partidoRepositorio.findById(partidoId).orElse(null);
    }

    public List<Usuario> obtenerJugadoresPorPartido(Long partidoId) {
        return usuarioRepositorio.findByPartidosId(partidoId);
    }

    public List<AsistenciaPartido> obtenerAsistenciasPorPartido(Long partidoId) {
        return asistenciaPartidoRepositorio.findByPartidoId(partidoId);
    }

    public void guardarAsistencias(Map<Long, String> asistencias, Long partidoId) {
        Partidos partido = obtenerPartidoPorId(partidoId);

        for (Map.Entry<Long, String> entry : asistencias.entrySet()) {
            Long jugadorId = entry.getKey();
            String estadoAsistencia = entry.getValue();

            Usuario jugador = usuarioRepositorio.findById(jugadorId).orElse(null);
            if (jugador != null) {
                AsistenciaPartido asistencia = new AsistenciaPartido();
                asistencia.setPartido(partido);
                asistencia.setJugador(jugador);
                asistencia.setAsistencia(estadoAsistencia);
                asistenciaPartidoRepositorio.save(asistencia);
            }
        }
    }
}
