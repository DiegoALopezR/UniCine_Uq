package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Pelicula implements Serializable {

   //PK
    @Id
    private String codigo;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false, length = 300)
    private String sinopsis;
    private String urlTrailer;
    private String urlImagen;
    @Column(nullable = false)
    private String estado;
    private String reparto;

    //FK
    @ToString.Exclude
    @OneToMany(mappedBy="pelicula")
    private List<Funcion> funciones;

   //FK
   @ToString.Exclude
    @OneToMany(mappedBy="pelicula")
    private List<Puntuacion> puntuaciones;

    //FK
    @ToString.Exclude
    @OneToMany(mappedBy="pelicula")
    private List<Foro> foro;

    //FK
    @ManyToMany(mappedBy = "peliculas")
    private List<Genero> generos;

    public Pelicula(String codigo, String nombre, String sinopsis, String urlTrailer, String urlImagen, String estado, String reparto, List<Genero> generos)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.sinopsis = sinopsis;
        this.urlTrailer = urlTrailer;
        this.urlImagen = urlImagen;
        this.estado = estado;
        this.reparto = reparto;
        this.generos = generos;
    }

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
                ", url_trailer='" + urlTrailer + '\'' +
                ", url_imagen='" + urlImagen + '\'' +
                ", estado='" + estado + '\'' +
                ", reparto='" + reparto + '\'' +
                '}';
    }
}
