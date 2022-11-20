package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, String>
{
    @Query("Select c From Ciudad c where c.nombre = :nombre")
    Ciudad obtener(String nombre);

    Ciudad findByNombre(String nombre);

    @Query("select c from Ciudad c where c.nombre = :nombre and c.teatros = :teatros")
    Ciudad comprobarAutenticacion(String nombre, String teatros);

    Ciudad findByNombreAndTeatros(String nombre, List<Teatro> teatros);

    //Esta consulta cuenta los teatros y los agrupa
    @Query("select ciudad.codigo, ciudad.nombre from Ciudad ciudad join ciudad.teatros tea group by ciudad.codigo")
    List<Object[]> contarTeatros();

    //Esta consulta verifica la ciudad dado el nombre
    Optional<Ciudad> findByNombreCiudad(String nombre);
}
