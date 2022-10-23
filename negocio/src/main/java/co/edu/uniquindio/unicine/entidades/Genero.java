package co.edu.uniquindio.unicine.test.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor

public class Genero implements Serializable {

   //PK
    @Id
    private String codigo;
    //@ElementCollection
    //private Map<String, String> nombres;
    private String nombre;

    @ManyToMany
    private List<Pelicula> peliculas;

    public Genero(String codigo, String nombre)
    {
        this.codigo = codigo;
        this.nombre = nombre;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genero genero = (Genero) o;

        return Objects.equals(codigo, genero.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "codigo='" + codigo + '\'' +
                ", nombre=" + nombre +
                '}';
    }
}
