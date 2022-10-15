package co.edu.uniquindio.unicine.entidades;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Cupon implements Serializable {

    @Id
    private String codigo;
    @PositiveOrZero
    @Column(nullable = false, length = 200)
    private String descripcion;
    @PositiveOrZero
    @Column(nullable = false)
    private String descuento;
    @Column(nullable = false)
    private String criterio;
    @Column(nullable = false)
    private LocalDateTime fecha_vencimiento;

    @OneToMany(mappedBy="cupon")
    private List<CuponCliente> cuponCliente;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cupon cupon = (Cupon) o;

        return Objects.equals(codigo, cupon.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Cupon{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", descuento='" + descuento + '\'' +
                ", criterio='" + criterio + '\'' +
                ", fecha_vencimiento=" + fecha_vencimiento +
                '}';
    }
}