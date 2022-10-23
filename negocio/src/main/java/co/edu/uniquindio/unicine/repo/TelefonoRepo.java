package co.edu.uniquindio.unicine.test.repo;

import co.edu.uniquindio.unicine.test.entidades.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonoRepo extends JpaRepository<Telefono, String>
{

}
