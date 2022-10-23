package co.edu.uniquindio.unicine.test.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

   //PK
    @Id
    private String codigo;
    private String respuesta;
    private String pregunta;

    //FK
    @OneToMany(mappedBy="foro")
    private List<Cliente> clientes;

    @ManyToOne
    private Pelicula pelicula;

    public Foro(String codigo, String respuesta, String pregunta, Pelicula pelicula)
    {
        this.codigo = codigo;
        this.respuesta = respuesta;
        this.pregunta = pregunta;
        this.pelicula = pelicula;
    }

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
