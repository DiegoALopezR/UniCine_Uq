package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EntradaRepo extends JpaRepository<Entrada, String>
{
    @Query("Select e From Entrada e where e.codigo = :codigo")
    EntradaRepo obtener(String codigo);

    EntradaRepo findByCodigo(String codigo);

    @Query("select e from Entrada e where e.codigo = :codigo and e.compra =:compra")
    EntradaRepo comprobarAutenticacion(String codigo, String compra);

    EntradaRepo findByCodigoAndCompra(String codigo, Compra compra);

}
