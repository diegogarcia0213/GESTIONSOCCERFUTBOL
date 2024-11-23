package co.ucentral.GestionFUTBOL.persistencia.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Partidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Relación OneToMany con AsistenciaPartido
    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AsistenciaPartido> asistencias;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<AsistenciaPartido> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<AsistenciaPartido> asistencias) {
        this.asistencias = asistencias;
    }
}

