package co.ucentral.GestionFUTBOL.servicio;


import co.ucentral.GestionFUTBOL.persistencia.entidades.AsistenciaPartidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.AsistenciaPartidosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsistenciaPartidosServicio {

    @Autowired
    private AsistenciaPartidosRepositorio asistenciaPartidosRepositorio;

    @Autowired
    private PartidosServicio partidosServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Método para guardar la asistencia de un jugador en un partido específico
    public void guardarAsistencia(Usuario jugador, Long partidoId, Boolean asistencia) {
        Partidos partido = partidosServicio.obtenerPartidoPorId(partidoId);
        if (partido != null) {
            AsistenciaPartidos asistenciaPartido = new AsistenciaPartidos();
            asistenciaPartido.setUsuario(jugador);
            asistenciaPartido.setPartido(partido);
            asistenciaPartido.setAsistencia(asistencia);

            asistenciaPartidosRepositorio.save(asistenciaPartido); // Guarda la asistencia en la base de datos
        }
    }
}

