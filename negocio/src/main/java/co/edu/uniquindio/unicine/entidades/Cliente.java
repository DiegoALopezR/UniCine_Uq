package co.edu.uniquindio.unicine.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString

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
    private Integer estado;


    //FK
    @ToString.Exclude
    @OneToMany(mappedBy="cliente")
    private List<Compra> compras;

    //FK
    @ToString.Exclude
    @OneToMany(mappedBy="clienteCupon")
    private List<CuponCliente> cuponClientes;

    //FK
    @ToString.Exclude
    @OneToMany(mappedBy="clienteP")
    private List<Puntuacion> puntuaciones;

    //FK
    @ToString.Exclude
    @OneToMany(mappedBy="clienteT")
    private List<Telefono> telefonos;

    @ManyToOne
    private Foro foro;

    public Cliente(String cedula, String nombreCompleto, String email, String contrasena)
    {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.contrasena = contrasena;

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

}

