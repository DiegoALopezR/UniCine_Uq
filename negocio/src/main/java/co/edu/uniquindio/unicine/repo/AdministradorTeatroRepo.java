package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AdministradorTeatroRepo extends JpaRepository<AdministradorTeatro, String>
{

    @Query("Select a From AdministradorTeatro a where a.correo = :correo")
    AdministradorTeatroRepo obtener(String correo);

    AdministradorTeatroRepo findByCorreo(String correo);

    @Query("select a from AdministradorTeatro a where a.correo = :correo and a.password = :password")
    AdministradorTeatroRepo comprobarAutenticacion(String correo, String password);

    AdministradorTeatroRepo findByCorreoAndPassword(String correo, String password);
}
