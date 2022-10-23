package co.edu.uniquindio.unicine.test.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor

public class Funcion implements Serializable{

    //PK
    @Id
    private String codigo;
    @PositiveOrZero
    @Column(nullable = false)
    private String precio;
    @ManyToOne
    private Sala sala;
    @ManyToOne
    private HorarioPelicula horarioPelicula;

    //FK
    @OneToMany(mappedBy="funcion")
    private List<Compra> comprasF;

    @ManyToOne
    private Pelicula pelicula;

    public Funcion(String codigo, String precio, Sala sala, HorarioPelicula horarioPelicula, Pelicula pelicula)
    {
        this.codigo = codigo;
        this.precio = precio;
        this.sala = sala;
        this.horarioPelicula = horarioPelicula;
        this.pelicula = pelicula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Funcion funcion = (Funcion) o;

        return Objects.equals(codigo, funcion.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Funcion{" +
                "codigo='" + codigo + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }
}
