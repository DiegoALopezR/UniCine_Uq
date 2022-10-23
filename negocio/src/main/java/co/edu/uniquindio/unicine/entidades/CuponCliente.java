package co.edu.uniquindio.unicine.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CuponCliente implements Serializable {

   //PK
    @Id
    private String codigo;
    @Column(nullable = false)
    private String estado;
    @ManyToOne
    private Cupon cupon;
    @ManyToOne
    private Cliente clienteCupon;
    @OneToOne
    private Compra compra;

    //FK
    @OneToOne(mappedBy="clienteCupon")
    private Compra compraCupon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CuponCliente that = (CuponCliente) o;

        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CuponCliente{" +
                "codigo='" + codigo + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
