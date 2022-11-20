package co.edu.uniquindio.unicine.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Compra implements Serializable {

   //PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @Column(nullable = false )
    private MedioPago medioPago;
    @Column(nullable = false )
    private LocalDateTime fechaCompra;
    @PositiveOrZero
    @Column(nullable = false )
    private Float precioTotal;

    //FK
    @ToString.Exclude
    @OneToMany(mappedBy="compra")
    private List<Entrada> entradas;

   //FK
    @ToString.Exclude
    @OneToMany(mappedBy="compraConfi")
    private List<CompraConfiteria> compraConfiteria;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Funcion funcion;

    @OneToOne
    private CuponCliente clienteCupon;

    public Compra(MedioPago medioPago, Cliente cliente, Funcion funcion)
    {
        this.medioPago = medioPago;
        this.cliente = cliente;
        this.funcion = funcion;
        this.fechaCompra =LocalDateTime.now();

    }

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
                ", medio_Pago='" + medioPago + '\'' +
                ", fecha_compra=" + fechaCompra +
                ", precio_total='" + precioTotal + '\'' +
                '}';
    }
}
