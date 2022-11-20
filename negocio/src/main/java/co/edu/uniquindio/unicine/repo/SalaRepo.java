package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepo extends JpaRepository<Sala,String>
{
    @Query("select s from Sala s where s.distribucionSillas= :codigo")
    Sala obtenerSala(Integer codigo);

}
