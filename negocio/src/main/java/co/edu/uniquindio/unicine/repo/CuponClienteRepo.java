package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.CuponCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponClienteRepo extends JpaRepository<CuponCliente, String>
{
    @Query("Select c From CuponCliente c where c.codigo = :codigo")
    CuponClienteRepo obtener(String codigo);

    CuponClienteRepo findByCodigo(String codigo);

    @Query("select c from CuponCliente c where c.codigo = :codigo and c.estado =:estado")
    CuponClienteRepo comprobarAutenticacion(String codigo, String estado);

    CuponClienteRepo findByCodigoAndEstado(String codigo, String estado);

}
