package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AdministradorRepo extends JpaRepository<Administrador, String>
{

    @Query("Select a From Administrador a where a.correo = :correo")
    Administrador obtener(String correo);

    Administrador findByCorreo(String Correo);

    @Query("select a from Administrador a where a.correo = :correo and a.password = :password")
    Administrador comprobarAutenticacion(String correo, String password);

    Administrador findByCorreoAndPassword(String correo, String password);



}
