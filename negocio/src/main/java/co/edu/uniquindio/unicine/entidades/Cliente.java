package co.edu.uniquindio.unicine.test.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor

public class Cliente implements Serializable {

    //PK
    @Id
    private String cedula;
    private String nombreCompleto;
    @Column(nullable = false, length = 35)
    private String email;
    @Column(nullable = false, length = 50)
    private String contrasena;
    private String imagen;
    @Column(nullable = false)
    private String estado;


    //FK
    @OneToMany(mappedBy="cliente")
    private List<Compra> compras;

    //FK
    @OneToMany(mappedBy="clienteCupon")
    private List<CuponCliente> cuponClientes;

    //FK
    @OneToMany(mappedBy="clienteP")
    private List<Puntuacion> puntuaciones;

    //FK
    @OneToMany(mappedBy="clienteT")
    private List<Telefono> telefonos;

    @ManyToOne
    private Foro foro;

    public Cliente(String cedula, String nombreCompleto, String email, String contrasena, String estado)
    {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.contrasena = contrasena;
        this.estado = estado;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        return Objects.equals(cedula, cliente.cedula);
    }

    @Override
    public int hashCode() {
        return cedula != null ? cedula.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cedula='" + cedula + '\'' +
                ", nombre_completo='" + nombreCompleto + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", imagen='" + imagen + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}

