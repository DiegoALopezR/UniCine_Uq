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

public class Sala implements Serializable{

    //PK
    @Id
    private String codigo;
    @Column(nullable = false)
    private String nombre;
    @ManyToOne
    private Teatro teatro;

    //FK
    @ToString.Exclude
    @OneToMany(mappedBy="sala")
    private List<Funcion> funciones;
    @ManyToOne
    private DistribucionSillas distribucionSillas;

    public Sala(String codigo, String nombre, Teatro teatro, DistribucionSillas distribucionSillas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.teatro = teatro;
        this.distribucionSillas = distribucionSillas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sala sala = (Sala) o;

        return Objects.equals(codigo, sala.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
