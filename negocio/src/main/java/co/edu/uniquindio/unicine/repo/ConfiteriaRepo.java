package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiteriaRepo extends JpaRepository<Confiteria, String>
{

    @Query("Select c From Confiteria c where c.codigo = :codigo")
    ConfiteriaRepo obtener(String codigo);

    ConfiteriaRepo findByCodigo(String codigo);

    @Query("select c from Confiteria c where c.codigo = :codigo and c.nombre =:nombre")
    ConfiteriaRepo comprobarAutenticacion(String codigo, String nombre);

    ConfiteriaRepo findByCodigoAndNombre(String codigo, Confiteria nombre);

}
