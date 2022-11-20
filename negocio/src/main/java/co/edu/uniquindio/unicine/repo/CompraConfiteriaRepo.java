package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.CompraConfiteria;
import co.edu.uniquindio.unicine.entidades.Confiteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraConfiteriaRepo extends JpaRepository<CompraConfiteria,String>
{

    @Query("Select c From CompraConfiteria c where c.codigo = :codigo")
    CompraConfiteria obtener(String codigo);

    CompraConfiteriaRepo findByCodigo(String codigo);

    @Query("select c from CompraConfiteria c where c.codigo = :codigo and c.confiteria =:confiteria")
    CompraConfiteria comprobarAutenticacion(String codigo, String confiteria);

    CompraConfiteria findByCodigoAndConfiteria(String codigo, Confiteria confiteria);
}
