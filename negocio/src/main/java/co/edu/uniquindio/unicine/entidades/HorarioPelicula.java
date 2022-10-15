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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class HorarioPelicula implements Serializable{

    @Id
    private String codigo;
    @Column(nullable = false)
    private String dia;
    @Column(nullable = false)
    private String hora;
    @Column(nullable = false)
    private LocalDateTime fecha_inicio;
    @Column(nullable = false)
    private LocalDateTime fecha_fin;

    @OneToMany(mappedBy="horarioPelicula")
    private List<Funcion> funciones;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HorarioPelicula that = (HorarioPelicula) o;

        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "HorarioPelicula{" +
                "codigo='" + codigo + '\'' +
                ", dia='" + dia + '\'' +
                ", hora='" + hora + '\'' +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                '}';
    }
}
