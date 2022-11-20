package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.CuponCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuponClienteRepo extends JpaRepository<CuponCliente, String>
{
    @Query("Select c From CuponCliente c where c.codigo = :codigo")
    CuponCliente obtener(String codigo);

    CuponCliente findByCodigo(String codigo);

    @Query("select c from CuponCliente c where c.codigo = :codigo and c.estado =:estado")
    CuponCliente comprobarAutenticacion(String codigo, String estado);

    CuponCliente findByCodigoAndEstado(String codigo, String estado);


    //Esta consulta obtiene todos los cupones del cliente y retorna ciertos parametros
    @Query("select cupCli.clienteCupon.cedula, cupCli.clienteCupon.nombreCompleto, cup from CuponCliente cupCli left join cupCli.cupon cup ")
    List<Object[]> obtenerCuponesTodosClientes();

    //Esta consulta busca el cupon de un cliente dado el codigo del cupon
    @Query("select cupCli from CuponCliente cupCli where cupCli.cupon.codigo = :codigoCupon")
    CuponCliente buscarCuponClientePorCodigoCupon(Integer codigoCupon);
}
