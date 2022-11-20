package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

public class CiudadTest
{
    @Test
    @Sql("classpath:dataset.sql")
    public void crearCiudadTest(){
        try {
            Ciudad ciudad = new Ciudad("Pereira");
            Ciudad ciudadCreada = administradorServicio.crearCiudad(ciudad);
            Assertions.assertNotNull(ciudadCreada);
            System.out.println(ciudadCreada.getNombreCiudad());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCiudadTest() throws Exception {
        try {
            Ciudad ciudadCreada = administradorServicio.obtenerCiudad(3);
            Assertions.assertNotNull(ciudadCreada);
            System.out.println(ciudadCreada.getNombreCiudad());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCiudadTest() throws Exception {

        try {
            Ciudad ciudadGuardada = administradorServicio.obtenerCiudad(4);
            System.out.println(ciudadGuardada.getNombreCiudad());
            ciudadGuardada.setNombreCiudad("Guajira");
            Ciudad ciudadActualizada = administradorServicio.actualizarCiudad(ciudadGuardada);
            Assertions.assertNotNull(ciudadActualizada);
            System.out.println(ciudadActualizada.getNombreCiudad());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCiudadTest() throws Exception {
        try {
            administradorServicio.eliminarCiudad(4);
            Ciudad ciudad = administradorServicio.obtenerCiudad(4);
            Assertions.assertNull(ciudad);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCiudad()  {
        List<Ciudad> ciudades = administradorServicio.listarCiudad();
        ciudades.forEach(System.out::println);
    }

}
