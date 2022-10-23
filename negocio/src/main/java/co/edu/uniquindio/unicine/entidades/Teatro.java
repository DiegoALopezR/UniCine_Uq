package co.edu.uniquindio.unicine.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString

public class Teatro implements Serializable {

    //PK
    @Id
    private String codigo;
    @Column(nullable = false)
    private String direccion;
    @Column(nullable = false, length = 10)
    private String telefono;
    @ManyToOne
    private Ciudad ciudad;
    @ManyToOne
    private AdministradorTeatro administradorTeatro;

    //FK
    @ToString.Exclude
    @OneToMany(mappedBy="teatro")
    private List<Sala> salas;

    public Teatro(String codigo, String direccion, String telefono, Ciudad ciudad, AdministradorTeatro administradorTeatro)
    {
        this.codigo = codigo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.administradorTeatro = administradorTeatro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teatro teatro = (Teatro) o;

        return Objects.equals(codigo, teatro.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Teatro{" +
                "codigo='" + codigo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}