package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, String>
{
    @Query("select c from Cliente c where c.email = ?1")

    Cliente obtener(String email);

    @Query("select c from Cliente c where c.email = :email and c.contrasena = :contrasena")
    Cliente comprobarAutentificacion (String email , String contrasena);

    @Query("select c from Cliente c where c.estado = :estado")
    List<Cliente> obtenerPorEstado (boolean estado, Pageable paginador);








}
