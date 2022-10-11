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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Compra implements Serializable {

    @Id
    private String codigo;
    @Column(nullable = false )
    private String medio_Pago;
    @Column(nullable = false )
    private LocalDateTime fecha_compra;
    @PositiveOrZero
    @Column(nullable = false )
    private String precio_total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Compra compra = (Compra) o;

        return Objects.equals(codigo, compra.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "codigo='" + codigo + '\'' +
                ", medi_Pago='" + medio_Pago + '\'' +
                ", fecha_compra=" + fecha_compra +
                ", precio_total='" + precio_total + '\'' +
                '}';
    }
}
