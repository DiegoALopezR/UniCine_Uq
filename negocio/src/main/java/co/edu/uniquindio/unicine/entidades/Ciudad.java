package co.edu.uniquindio.unicine.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor

public class Ciudad implements Serializable
{

    @Id
    private String codigo;
    @Column(nullable = false)
    private String nombre;

    //FK
    @ToString.Exclude
    @OneToMany(mappedBy="ciudad")
    private List<Teatro> teatros;


    public Ciudad(String codigo, String nombre)
    {
        this.codigo = codigo;
        this.nombre = nombre;

    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ciudad ciudad = (Ciudad) o;

        return Objects.equals(codigo, ciudad.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "Ciudad{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
