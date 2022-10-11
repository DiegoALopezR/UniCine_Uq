package co.edu.uniquindio.unicine.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
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
