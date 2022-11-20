package co.edu.uniquindio.unicine.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString

public class HorarioPelicula implements Serializable{

    //PK
    @Id
    private String codigo;
    @Column(nullable = false)
    private String dia;
    @Column(nullable = false)
    private LocalTime hora;
    @Column(nullable = false)
    private LocalDateTime fechaInicio;
    @Column(nullable = false)
    private LocalDateTime fechaFin;

    //FK
    @ToString.Exclude
    @OneToMany(mappedBy="horarioPelicula")
    private List<Funcion> funciones;

    public HorarioPelicula(String codigo, String dia, LocalTime hora, LocalDateTime fechaInicio, LocalDateTime fechaFin)
    {
        this.codigo = codigo;
        this.dia = dia;
        this.hora = hora;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

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
                ", fecha_inicio=" + fechaInicio +
                ", fecha_fin=" + fechaFin +
                '}';
    }
}
