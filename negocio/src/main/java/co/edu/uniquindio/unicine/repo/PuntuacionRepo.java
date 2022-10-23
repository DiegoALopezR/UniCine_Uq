package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntuacionRepo extends JpaRepository<Puntuacion, String>
{


}
