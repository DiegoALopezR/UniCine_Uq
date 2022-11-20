package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.HorarioPelicula;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.Sala;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class FuncionTest
{
    @Test
    @Sql("classpath:dataset.sql")
    public void crearFuncion() throws Exception{

        Pelicula pelicula = administradorServicio.obtenerPelicula(3);
        HorarioPelicula horario = new HorarioPelicula(null, Time.valueOf(LocalTime.now()), LocalDate.now(), LocalDate.now());
        Sala sala = adminTeatroServicio.obtenerSala(3);
        Funcion funcion = Funcion.builder().precio(3500.00).pelicula(pelicula).horario(horario).sala(sala).build();

        try {
            Funcion funcionCreada = adminTeatroServicio.crearFuncion(funcion);
            Assertions.assertNotNull(funcionCreada);
            System.out.println(funcionCreada.getCodigo());
            System.out.println(funcionCreada.getSala());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionTest() throws Exception {

        try {
            Funcion funcionCreado = adminTeatroServicio.obtenerFuncion(3);
            Assertions.assertNotNull(funcionCreado);
            System.out.println(funcionCreado.getCodigo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actuaizarFuncionTest() throws Exception {

        try {
            Funcion funcionGuardado = adminTeatroServicio.obtenerFuncion(3);
            System.out.println(funcionGuardado.getCodigo() + " precio : " + funcionGuardado.getPrecio());
            funcionGuardado.setPrecio(12000.00);
            Funcion funcionActualizada = adminTeatroServicio.actuaizarFuncion(funcionGuardado);
            Assertions.assertNotNull(funcionActualizada);
            System.out.println(funcionActualizada.getPrecio());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarFuncionTest() throws Exception {

        try {
            adminTeatroServicio.eliminarFuncion(3);
            Funcion funcion = adminTeatroServicio.obtenerFuncion(3);
            Assertions.assertNull(funcion);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFuncionTest() {
        List<Funcion> funciones = adminTeatroServicio.listarFuncion();
        funciones.forEach(System.out::println);
    }


}
