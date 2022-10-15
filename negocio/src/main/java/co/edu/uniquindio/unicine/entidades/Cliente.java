package co.edu.uniquindio.unicine.entidades;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor

public class Cliente implements Serializable {

    @Id
    private String cedula;
    private String nombre_completo;
    @Column(nullable = false, length = 35)
    private String email;
    @Column(nullable = false, length = 50)
    private String contrasena;
    private String imagen;
    @Column(nullable = false)
    private String estado;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy="cliente")
    private List<Compra> compras;

    @OneToMany(mappedBy="clienteCupon")
    private List<CuponCliente> cuponClientes;

    @OneToMany(mappedBy="clienteP")
    private List<Puntuacion> puntuaciones;

    @OneToMany(mappedBy="clienteT")
    private List<Telefono> telefonos;

    @ManyToOne
    private Foro foro;

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
                ", nombre_completo='" + nombre_completo + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", imagen='" + imagen + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}

