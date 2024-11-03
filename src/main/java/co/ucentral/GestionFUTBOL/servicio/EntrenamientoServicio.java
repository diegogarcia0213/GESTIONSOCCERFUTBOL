package co.ucentral.GestionFUTBOL.servicio;



import co.ucentral.GestionFUTBOL.persistencia.entidades.Entrenamientos;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.EntrenamientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;



import co.ucentral.GestionFUTBOL.persistencia.entidades.Entrenamientos;
import co.ucentral.GestionFUTBOL.persistencia.repositorio.EntrenamientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

    @Service
    public class EntrenamientoServicio {

        @Autowired
        private EntrenamientoRepositorio entrenamientoRepositorio;

        // Método para registrar un entrenamiento
        public Entrenamientos registrarEntrenamiento(LocalDate fecha, String categoria) {
            Entrenamientos entrenamiento = new Entrenamientos();
            entrenamiento.setFecha(fecha);
            entrenamiento.setCategoria(categoria);
            return entrenamientoRepositorio.save(entrenamiento);
        }

        // Método para listar entrenamientos
        public List<Entrenamientos> listarEntrenamientos() {
            return entrenamientoRepositorio.findAllByOrderByFechaDesc();
        }

        // Método para eliminar un entrenamiento por ID
        public void eliminarEntrenamiento(Long id) {
            entrenamientoRepositorio.deleteById(id);
        }
    }

