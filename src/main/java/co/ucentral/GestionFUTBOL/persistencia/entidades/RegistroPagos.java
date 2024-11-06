package co.ucentral.GestionFUTBOL.persistencia.entidades;


import jakarta.persistence.*;

@Entity
public class RegistroPagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    private String estadoSuscripcion = "Inactivo";
    private String modoSuscripcion = "Red"; // Estado inicial en rojo
    private Integer monto;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEstadoSuscripcion() {
        return estadoSuscripcion;
    }

    public void setEstadoSuscripcion(String estadoSuscripcion) {
        this.estadoSuscripcion = estadoSuscripcion;
    }

    public String getModoSuscripcion() {
        return modoSuscripcion;
    }

    public void setModoSuscripcion(String modoSuscripcion) {
        this.modoSuscripcion = modoSuscripcion;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }
}
