package co.ucentral.GestionFUTBOL.persistencia.entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    private String estadoSuscripcion = "Inactivo";

    // Relación OneToMany con Partidos
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Partidos> partidos;

    // Relación OneToMany con Entrenamientos
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Entrenamientos> entrenamientos;

    // Relación OneToMany con AsistenciaPartido
    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AsistenciaPartido> asistencias;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getEstadoSuscripcion() {
        return estadoSuscripcion;
    }

    public void setEstadoSuscripcion(String estadoSuscripcion) {
        this.estadoSuscripcion = estadoSuscripcion;
    }

    public List<Partidos> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partidos> partidos) {
        this.partidos = partidos;
    }

    public List<Entrenamientos> getEntrenamientos() {
        return entrenamientos;
    }

    public void setEntrenamientos(List<Entrenamientos> entrenamientos) {
        this.entrenamientos = entrenamientos;
    }

    public List<AsistenciaPartido> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<AsistenciaPartido> asistencias) {
        this.asistencias = asistencias;
    }

    public enum Rol {
        JUGADOR,
        ENTRENADOR
    }
}
