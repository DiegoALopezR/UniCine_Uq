package co.edu.uniquindio.unicine.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.security.PrivateKey;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Pelicula implements Serializable {

    @Id
    private String codigo;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false, length = 300)
    private String sinopsis;
    private String url_trailer;
    private String url_imagen;
    @Column(nullable = false)
    private String estado;
    private String reparto;

    @OneToMany(mappedBy="pelicula")
    private List<Funcion> funciones;
    @ManyToOne
    private Foro foroP;

    @OneToMany(mappedBy="pelicula")
    private List<Puntuacion> puntuaciones;

    @ManyToMany(mappedBy = "peliculas")
    private List<Genero> generos; 

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pelicula pelicula = (Pelicula) o;

        return Objects.equals(codigo, pelicula.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", url_trailer='" + url_trailer + '\'' +
                ", url_imagen='" + url_imagen + '\'' +
                ", estado='" + estado + '\'' +
                ", reparto='" + reparto + '\'' +
                '}';
    }
}
