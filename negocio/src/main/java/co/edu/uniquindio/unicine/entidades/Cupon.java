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
@NoArgsConstructor

public class Cupon implements Serializable {

    //FK
    @Id
    private String codigo;
    @PositiveOrZero //why?
    @Column(nullable = false, length = 200)
    private String descripcion;
    @PositiveOrZero
    @Column(nullable = false)
    private double descuento;
    @Column(nullable = false)
    private String criterio;
    @Column(nullable = false)
    private LocalDateTime fechaVencimiento;

    //FK
    @ToString.Exclude
    @OneToMany(mappedBy="cupon")
    private List<CuponCliente> cuponCliente;

    public Cupon(String codigo, String descripcion, double descuento, String criterio, LocalDateTime fechaVencimiento)
    {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.criterio = criterio;
        this.fechaVencimiento = fechaVencimiento;

    }

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
                ", fecha_vencimiento=" + fechaVencimiento +
                '}';
    }
}