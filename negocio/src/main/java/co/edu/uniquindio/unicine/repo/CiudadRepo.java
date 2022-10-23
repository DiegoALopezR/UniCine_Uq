package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepo extends JpaRepository<Ciudad, String>
{
    @Query("Select c From Ciudad c where c.nombre = :nombre")
    CiudadRepo obtener(String nombre);

    CiudadRepo findByNombre(String nombre);

    @Query("select c from Ciudad c where c.nombre = :nombre and c.teatros = :teatros")
    CiudadRepo comprobarAutenticacion(String nombre, String teatros);

    CiudadRepo findByNombreAndTeatros(String nombre, List<Teatro> teatros);

}
