package co.edu.uniquindio.unicine.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class CompraConfiteria implements Serializable {

    @Id
    private String codigo;
    @PositiveOrZero
    @Column(nullable = false )
    private String precio;
    @PositiveOrZero
    @Column(nullable = false)
    private String unidades;

    @ManyToOne
    private Confiteria confiteria;

    @ManyToOne
    private Compra compraConfi;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompraConfiteria that = (CompraConfiteria) o;

        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "compraConfiteria{" +
                "codigo='" + codigo + '\'' +
                ", precio='" + precio + '\'' +
                ", unidades='" + unidades + '\'' +
                '}';
    }
}
