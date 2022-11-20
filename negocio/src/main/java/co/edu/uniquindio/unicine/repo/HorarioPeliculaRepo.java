package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.HorarioPelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface HorarioPeliculaRepo extends JpaRepository<HorarioPelicula, String>
{
    // Esta consulta validad el horario dado el codigo

    @Override
    Optional<HorarioPelicula> findById(String string);

    // Esta consulta validad el horario dado la hora
    Optional<HorarioPelicula> findByHora (LocalTime hora);

}
