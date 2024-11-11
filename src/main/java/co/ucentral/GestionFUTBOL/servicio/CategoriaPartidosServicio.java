package co.ucentral.GestionFUTBOL.servicio;

import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.entidades.Usuario;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.CategoriaPartidosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaPartidosServicio {

    private final CategoriaPartidosRepositorio categoriaPartidosRepositorio;

    @Autowired
    public CategoriaPartidosServicio(CategoriaPartidosRepositorio categoriaPartidosRepositorio) {
        this.categoriaPartidosRepositorio = categoriaPartidosRepositorio;
    }

    // Método para obtener los partidos de la categoría "adultos" registrados por usuarios con rol "jugador"
    public List<Partidos> obtenerPartidosAdultosPorJugadores() {
        List<Partidos> partidosAdultos = categoriaPartidosRepositorio.findByCategoria("adultos");
        return partidosAdultos.stream()
                .filter(partido -> partido.getUsuario().getRol().name().equalsIgnoreCase("jugador"))
                .collect(Collectors.toList());
    }
}



