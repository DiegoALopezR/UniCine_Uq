package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepo extends JpaRepository<Genero, String>
{
    @Query("Select g From Genero g where g.codigo = :codigo")
    Genero obtener(String nombre);

    Genero findByNombre(String nombre);

    @Query("select g from Genero g where g.codigo = :nombre and g.nombre =:nombre")
    Genero comprobarAutenticacion(String codigo, String compra);

    Genero findByCodigoAndNombre(String codigo, String nombre);

}
