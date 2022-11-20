package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntradaRepo extends JpaRepository<Entrada, String>
{
    @Query("Select e From Entrada e where e.codigo = :codigo")
    Entrada obtener(String codigo);

    EntradaRepo findByCodigo(String codigo);

    @Query("select e from Entrada e where e.codigo = :codigo and e.compra =:compra")
    Entrada comprobarAutenticacion(String codigo, String compra);

    Entrada findByCodigoAndCompra(String codigo, Compra compra);

    //Esta consulta obtiene una lista de todas las entredad dado el codigo de la compra
    @Query("select comp.entradas from Compra comp where comp.codigo = :codigo")
    List<Entrada> obtenerEntradasCompra(Integer codigo);

}
