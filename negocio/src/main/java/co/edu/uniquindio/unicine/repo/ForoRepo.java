package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Foro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForoRepo extends JpaRepository<Foro,String>
{
    @Query("Select f From Foro f where f.codigo = :codigo")
    Foro obtener(String codigo);

    Foro findByCodigo(String codigo);

    @Query("select f from Foro f where f.codigo = :codigo")
    Foro comprobarAutenticacion(String codigo, String clientes);

    Foro findByCodigoAndClientes(String codigo, List<Cliente> clientes);

}
