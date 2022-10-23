package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, String>
{

    @Query("Select c From Compra c where c.entradas = :entradas")
    CompraRepo obtener(String entrada);

    CompraRepo findByEntradas(List<Entrada> entradas);

    @Query("select c from Compra c where c.entradas = :entradas and c.codigo =:codigo ")
    CompraRepo comprobarAutenticacion(String entradas, String codigo);

    CompraRepo findByEntradasAndCodigo(List<Entrada> entradas, String codigo);

}
