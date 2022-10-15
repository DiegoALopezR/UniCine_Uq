package co.edu.uniquindio.unicine.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Foro implements Serializable {

    @Id
    private String codigo;
    private String respuesta;
    private String pregunta;

    @OneToMany(mappedBy="foro")
    private List<Cliente> clientes;

    @OneToMany(mappedBy="foroP")
    private List<Pelicula> peliculas;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foro foro = (Foro) o;

        return Objects.equals(codigo, foro.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Foro{" +
                "codigo='" + codigo + '\'' +
                ", respuesta='" + respuesta + '\'' +
                ", pregunta='" + pregunta + '\'' +
                '}';
    }
}
