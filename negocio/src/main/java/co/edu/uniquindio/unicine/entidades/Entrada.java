package co.edu.uniquindio.unicine.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Entrada {

    @Id
    private String codigo;
    @PositiveOrZero
    @Column(nullable = false)
    private String fila;
    @PositiveOrZero
    @Column(nullable = false)
    private String columna;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entrada entrada = (Entrada) o;

        return Objects.equals(codigo, entrada.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Entrada{" +
                "codigo='" + codigo + '\'' +
                ", fila='" + fila + '\'' +
                ", columna='" + columna + '\'' +
                '}';
    }
}
