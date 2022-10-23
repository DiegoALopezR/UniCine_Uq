package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.DistribucionSillas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DistribucionSillasRepo extends JpaRepository<DistribucionSillas, String>
{
    @Query("Select d From DistribucionSillas d where d.codigo = :codigo")
    DistribucionSillasRepo obtener(String codigo);

    DistribucionSillasRepo findByCodigo(String codigo);

    @Query("select d from DistribucionSillas d where d.codigo = :codigo and d.totalSillas =:totalSillas")
    DistribucionSillasRepo comprobarAutenticacion(String codigo, String totalSillas);

    DistribucionSillasRepo findByCodigoAndTotalSillas(String codigo, String totalSillas);

}
