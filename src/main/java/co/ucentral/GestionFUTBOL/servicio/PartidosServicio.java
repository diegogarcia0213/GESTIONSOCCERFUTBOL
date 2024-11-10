package co.ucentral.GestionFUTBOL.servicio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.PartidosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class PartidosServicio {

    @Autowired
    private PartidosRepositorio partidosRepositorio;

    public void registrarPartido(Long usuarioId, LocalDate fecha, String categoria) {
        Partidos partido = new Partidos();
        partido.setUsuarioId(usuarioId);
        partido.setFecha(fecha);
        partido.setCategoria(categoria);
        partidosRepositorio.save(partido);
    }

    public List<Partidos> listarPartidosPorUsuario(Long usuarioId) {
        return partidosRepositorio.findAllByUsuarioIdOrderByFechaDesc(usuarioId);
    }

    public void eliminarPartido(Long id) {
        partidosRepositorio.deleteById(id);
    }
}
