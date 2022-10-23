package co.edu.uniquindio.unicine.test.entidades;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor

public class AdministradorTeatro implements Serializable {

    //PK
    @Id
    private String codigo;
    @Column(nullable = false)
    private String correo;
    @Column(nullable = false)
    private String password;

    //FK
    @OneToMany(mappedBy="administradorTeatro")
    private List<Teatro> teatros;

    public AdministradorTeatro(String codigo, String correo, String password)
    {
        this.codigo = codigo;
        this.correo = correo;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdministradorTeatro that = (AdministradorTeatro) o;

        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AdministradorTeatro{" +
                "codigo='" + codigo + '\'' +
                ", correo='" + correo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
