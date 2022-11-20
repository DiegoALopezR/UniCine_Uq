package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.DistribucionSillas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DistribucionSillasRepo extends JpaRepository<DistribucionSillas, String>
{
    @Query("Select d From DistribucionSillas d where d.codigo = :codigo")
    DistribucionSillas obtener(String codigo);

    DistribucionSillas findByCodigo(String codigo);

    @Query("select d from DistribucionSillas d where d.codigo = :codigo and d.totalSillas =:totalSillas")
    DistribucionSillas comprobarAutenticacion(String codigo, String totalSillas);

    DistribucionSillas findByCodigoAndTotalSillas(String codigo, String totalSillas);



}
