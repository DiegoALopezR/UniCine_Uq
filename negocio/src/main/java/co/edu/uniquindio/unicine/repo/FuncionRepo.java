package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionRepo extends JpaRepository<Funcion, String>
{
    //Esta conuslta obtiene el nomnre de una pelicula que esta en una funcion dado el codigo de la funcion
    @Query("select f.pelicula.nombre from Funcion f where f.codigo = :codigoFuncion")
    String obtenerNombrePelicula(Integer codigoFuncion);

    // Esta consulta obtiene una pelicula de una funcion y elimina los repetidos
    @Query("select distinct f.pelicula from Funcion f")
    List<Pelicula> obtenerPelicula();

    //Esta consulta retora los atributos de la FuncionDTO y busca una funcion dado el codigo de la pelicula
    // @Query("select new  co.edu.uniquindio.unicine.dto.FuncionDTO(f.pelicula.nombre, f.pelicula.estado, f.pelicula.imagen, f.sala.numeroSala, f.sala.teatro.direccion, f.sala.teatro.ciudad.nombreCiudad, f.horario) from Funcion f where f.pelicula.codigo = :codigo")
    //List<FuncionDTO> listarFunciones(Integer codigo);

    //Esta funcion retorna todas funciones que no tienen compra
    @Query("select funcion from Funcion funcion where funcion.sala.teatro.codigo = :codigo and funcion.comprasF is empty ")
    List<Funcion> obtenerFuncionesSinCompra(Integer codigo);

    // Esta consulta retorna la funcion dado el codigo del teatro y las fechas de inicio y fin
    // @Query("select f from Funcion f where f.sala.teatro.nit = :codigoTeatro and f.horario.fechaInicio < :fechafin or f.horario.fechaFinal > :fechaInicio")
    //List<Funcion> obtenerFuncionesTeatro(Integer codigoTeatro, LocalDate fechaInicio, LocalDate fechaFin);

    //Esta consulta obtiene una sala de una funcion dado el codigo de la sala
    @Query("select funcion.sala from Funcion funcion where funcion.sala.nombre = :codigoSala")
    Optional<Funcion> buscarSala(Integer codigoSala);

    //Esta consulta obtiene una funcion dado el codigo de la sala, los dias de la semana y la hora
    @Query("select f from Funcion f where f.sala.nombre = :numeroSala and :dIaSemana in f.horarioPelicula.dia and f.horarioPelicula.hora = :hora")
    List<Funcion> obtenerFuncionesHorario(String nombre, String dIaSemana, String hora);

    //Esta consulta muestra todas las entradas que tiene una funcion dado el codigo de la funcion
    @Query("select entradas from Funcion funcion, IN (funcion.comprasF) compras, IN(compras.entradas) entradas where funcion.codigo = :codigo")
    List<Entrada> listaEntradas(Integer codigo);

    //Esta consulta muestra todas las compras que tiene una funcion dado el codigo de la funcion
    @Query("select compras from Funcion funcion, IN (funcion.comprasF) compras where funcion.codigo = :codigo")
    List<Compra> listaCompra (Integer codigo);

    //Esta consulta muestra las entradas disponibles con sillas de una funcion validando el codigo de la funcion, la fila y la colunma
    @Query("select entradas from Funcion funcion, IN (funcion.comprasF) compras, IN(compras.entradas) entradas where funcion.codigo = :codigo and entradas.fila = :fila and entradas.columna = :columna")
    List<Entrada> verificarSillas(Integer codigo, Integer fila, Integer columna);

    //Esta consulta muestra la entradasdisponibles con sillas de una funcion validando el codigo de la funcion, la fila y la colunma
    @Query("select entrada from Funcion funcion, IN (funcion.comprasF) compras, IN(compras.entradas) entrada where funcion.codigo = :codigo and entrada.fila = :fila and entrada.columna = :columna")
    Entrada verificarSilla(String codigo, @PositiveOrZero String fila, @PositiveOrZero String columna);


    @Query("select distinct f.pelicula from Funcion f where f.sala.teatro.ciudad.codigo = :codigoPostal and f.pelicula.estado = :estadoPelicula")
    List<Pelicula> listarPeliculaEstadoCiudad(Integer codigoPostal, Estado estadoPelicula);

    @Query("select distinct f.pelicula from Funcion f where  f.pelicula.estado = :estadoPelicula")
    List<Pelicula> listarPeliculaEstado(Estado estadoPelicula);



}
