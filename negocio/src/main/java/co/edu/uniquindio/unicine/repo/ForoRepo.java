package co.edu.uniquindio.unicine.test.repo;

import co.edu.uniquindio.unicine.test.entidades.Foro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForoRepo extends JpaRepository<Foro,String>
{

}
