package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.CompraConfiteria;
import co.edu.uniquindio.unicine.entidades.Confiteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfiteriaRepo extends JpaRepository<Confiteria, String>
{

    @Query("Select c From Confiteria c where c.codigo = :codigo")
    Confiteria obtener(String codigo);

    Confiteria findByCodigo(String codigo);

    @Query("select c from Confiteria c where c.codigo = :codigo and c.nombre =:nombre")
    Confiteria comprobarAutenticacion(String codigo, String nombre);

    Confiteria findByCodigoAndNombre(String codigo, Confiteria nombre);

    //Esta consulta obtiene las compras que hay en esa entidad dado el codigo de la compra
    @Query("select cp from CompraConfiteria  cp where cp.compraConfi.codigo = :codigo")
    List<CompraConfiteria> obtenerComprasConfit(Integer codigo);

}
