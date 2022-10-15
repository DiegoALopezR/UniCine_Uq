package co.edu.uniquindio.unicine.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class DistribucionSillas implements Serializable {

    @Id
    private String codigo;
    @Column(nullable = false)
    private String esquema;
    @PositiveOrZero
    @Column(nullable = false)
    private String total_sillas;
    @PositiveOrZero
    @Column(nullable = false)
    private String filas;
    @PositiveOrZero
    @Column(nullable = false)
    private String columnas;

    @OneToMany(mappedBy="distribucionSillas")
    private List<Sala> salas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistribucionSillas that = (DistribucionSillas) o;

        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DistribucionSillas{" +
                "codigo='" + codigo + '\'' +
                ", esquema='" + esquema + '\'' +
                ", total_sillas='" + total_sillas + '\'' +
                ", filas='" + filas + '\'' +
                ", columnas='" + columnas + '\'' +
                '}';
    }
}
