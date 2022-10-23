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
    ForoRepo obtener(String codigo);

    ForoRepo findByCodigo(String codigo);

    @Query("select f from Foro f where f.codigo = :codigo and f.clientes =:cliente")
    ForoRepo comprobarAutenticacion(String codigo, String clientes);

    ForoRepo findByCodigoAndClientes(String codigo, List<Cliente> clientes);

}
