package co.edu.uniquindio.unicine.test.repo;

import co.edu.uniquindio.unicine.test.entidades.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepo extends JpaRepository<Genero, String>
{

}