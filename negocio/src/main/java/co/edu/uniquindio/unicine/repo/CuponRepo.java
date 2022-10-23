package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponRepo extends JpaRepository<Cupon,String>
{
    @Query("Select c From Cupon c where c.codigo = :codigo")
    CuponRepo obtener(String codigo);

    CuponRepo findByCodigo(String codigo);

    @Query("select c from Cupon c where c.codigo = :codigo and c.descripcion =:descripcion")
    CuponRepo comprobarAutenticacion(String codigo, String descripcion);

    CuponRepo findByCodigoAndDescripcion(String codigo, String descripcion);

}
