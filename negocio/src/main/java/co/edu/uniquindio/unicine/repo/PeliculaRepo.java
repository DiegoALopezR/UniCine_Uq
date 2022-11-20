package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepo extends JpaRepository<Pelicula,String>
{
    // Esta consulta valida si existe la pelicula dado el nombre el pelicula
    List<Pelicula> findByNombre(String nombrePelicula);

    // Esta consulta valida si existe la pelicula dado el nombre el pelicula
    @Query("select pelicula from Pelicula pelicula where pelicula.nombre = :nombrePelicula")
    Pelicula buscarPeliculaPorNombre(String nombrePelicula);

    // Esta consulta obtiene una pelicula dado el estado de la pelicula
    @Query("select p from Pelicula p where p.estado = :estado")
    List<Pelicula> obtenerPeliculasPorEstado(Boolean estado);

    // Esta consulta obtiene una pelicula dado el genero de la pelicula
    @Query("select p from Pelicula p where p.generos = :genero")
    List<Pelicula> obtenerPeliculasPorGenero(Genero generos);

    // Esta consulta obtiene una pelicula dado el estado de la pelicula y el nombre de la pelicula o una parte del nombre
    @Query("select pelicula from Pelicula pelicula where pelicula.nombre like concat('%', :nombre, '%') and pelicula.estado = :estado")
    List<Pelicula> buscarPelicula(String nombre, Boolean estado);

    // Esta consulta obtiene una pelicula dado el estado de la pelicula y el nombre de la pelicula o una parte del nombre (2)
    @Query("select pelicula from Pelicula pelicula where pelicula.nombre like concat('%', :nombre, '%') and pelicula.estado = :estado")
    Optional<Pelicula> buscarPeliculaEstado(String nombre, Boolean estado);

    //Esta consulta retorna los atributos del horarioSalaDTO y retorna una funcion dado el codigo de la pelicula y codigo del teatro
    //@Query("select new co.edu.uniquindio.unicine.dto.HorarioSalaDTO(funcion.horario, funcion.sala) from Pelicula pelicula join pelicula.funciones funcion where pelicula.codigo = :codigoPelicula and funcion.sala.teatro.nit = :codigoTeatro")
    //List<HorarioSalaDTO> listarHorario(Integer codigoPelicula, Integer codigoTeatro);

    //Esta consulta retorna una lista de peliculas dado el genero de las peliculas
    @Query("select pelicula from Pelicula pelicula where :genero member of pelicula.generos order by pelicula.nombre asc")
    List<Pelicula> listarPeliculas(Genero generos);


}
