package co.edu.uniquindio.unicine.test.repo;

import co.edu.uniquindio.unicine.test.entidades.Confiteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiteriaRepo extends JpaRepository<Confiteria, String>
{


}