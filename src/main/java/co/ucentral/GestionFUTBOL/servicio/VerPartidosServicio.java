package co.ucentral.GestionFUTBOL.servicio;


import co.ucentral.GestionFUTBOL.persistencia.entidades.Partidos;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.VerPartidosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerPartidosServicio {

    @Autowired
    private VerPartidosRepositorio verPartidosRepositorio;

    // Método para listar todos los partidos
    public List<Partidos> listarPartidos() {
        return verPartidosRepositorio.findAll();
    }

    // Puedes agregar otros métodos si necesitas funcionalidades adicionales
}
