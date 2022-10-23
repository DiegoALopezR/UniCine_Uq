package co.edu.uniquindio.unicine.test.entidades;

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
@NoArgsConstructor

public class Confiteria implements Serializable {

  //PK
    @Id
    private String codigo;
    @Column(nullable = false)
    private String nombre;
    @PositiveOrZero
    @Column(nullable = false)
    private String precio;
    private String urlImagen;

    //FK
    @OneToMany(mappedBy="confiteria")
    private List<CompraConfiteria> compraConfiteria;

    public Confiteria(String codigo, String nombre, String precio, String urlImagen)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.urlImagen = urlImagen;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Confiteria that = (Confiteria) o;

        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Confiteria{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio='" + precio + '\'' +
                ", url_Imagen='" + urlImagen + '\'' +
                '}';
    }
}
