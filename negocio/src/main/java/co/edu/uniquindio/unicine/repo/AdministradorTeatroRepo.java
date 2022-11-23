package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdministradorTeatroRepo extends JpaRepository<AdministradorTeatro, String>
{

    @Query("Select a From AdministradorTeatro a where a.correo = :correo")
    AdministradorTeatro obtener(String correo);

    AdministradorTeatro findByCorreo(String correo);

    @Query("select a from AdministradorTeatro a where a.correo = :correo and a.password = :password")
    AdministradorTeatro comprobarAutenticacion(String correo, String password);

    AdministradorTeatro findByCorreoAndPassword(String correo, String password);


    //Esta consulta validad si existe un administradorTeatro ingresando la cedula
    Optional<AdministradorTeatro> findByCodigo(Integer codigo);

}
