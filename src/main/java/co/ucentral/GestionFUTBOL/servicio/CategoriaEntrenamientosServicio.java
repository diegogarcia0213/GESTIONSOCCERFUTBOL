package co.ucentral.GestionFUTBOL.servicio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Entrenamientos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.CategoriaEntrenamientosRepositorio;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CategoriaEntrenamientosServicio {

    @Autowired
    private CategoriaEntrenamientosRepositorio entrenamientosRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio; // Repositorio para manejar datos de usuario

    // Método para obtener entrenamientos de la categoría "adolescentes" con rol "JUGADOR"
    public List<Entrenamientos> obtenerEntrenamientosPorCategoriaAdolescentes() {
        return entrenamientosRepositorio.findEntrenamientosByCategoria("adolescentes");
    }

    // Método para obtener usuarios que seleccionaron una fecha específica en una categoría
    public List<Usuario> obtenerUsuariosPorFechaYCategoria(LocalDate fecha, String categoria) {
        return usuarioRepositorio.findUsuariosByFechaAndCategoria(fecha, categoria);
    }
}
