package co.edu.uniquindio.unicine.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Puntuacion implements Serializable {

    @Id
    private String codigo;
    private String puntuacion;
    @ManyToOne
    private Cliente clienteP;

    @ManyToOne
    private Pelicula pelicula;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Puntuacion that = (Puntuacion) o;

        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Puntuacion{" +
                "codigo='" + codigo + '\'' +
                ", puntuacion='" + puntuacion + '\'' +
                '}';
    }
}
