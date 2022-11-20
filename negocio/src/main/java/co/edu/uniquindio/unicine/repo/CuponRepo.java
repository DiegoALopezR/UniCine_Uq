package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuponRepo extends JpaRepository<Cupon,String>
{
    @Query("Select c From Cupon c where c.codigo = :codigo")
    Cupon obtener(String codigo);

    Cupon findByCodigo(String codigo);

    @Query("select c from Cupon c where c.codigo = :codigo and c.descripcion =:descripcion")
    Cupon comprobarAutenticacion(String codigo, String descripcion);

    Cupon findByCodigoAndDescripcion(String codigo, String descripcion);

    //Esta consulta obtiene los cupones de un cliente dado su cedula
    @Query("select cli.cuponClientes from Cliente cli where cli.cedula = :cedula")
    List<Cupon> obtenerCuponesCliente(Integer cedula);

}
