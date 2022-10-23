package co.edu.uniquindio.unicine.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@NoArgsConstructor
@ToString

public class DistribucionSillas implements Serializable {

   //PK
    @Id
    private String codigo;
    @Column(nullable = false)
    private String esquema;
    @PositiveOrZero
    @Column(nullable = false)
    private String totalSillas;
    @PositiveOrZero
    @Column(nullable = false)
    private String filas;
    @PositiveOrZero
    @Column(nullable = false)
    private String columnas;

   //FK
    @ToString.Exclude
    @OneToMany(mappedBy="distribucionSillas")
    private List<Sala> salas;

    public DistribucionSillas(String codigo, String esquema, String totalSillas, String filas, String columnas)
    {
        this.codigo = codigo;
        this.esquema = esquema;
        this.totalSillas = totalSillas;
        this.filas = filas;
        this.columnas = columnas;
    }

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
                ", total_sillas='" + totalSillas + '\'' +
                ", filas='" + filas + '\'' +
                ", columnas='" + columnas + '\'' +
                '}';
    }
}
