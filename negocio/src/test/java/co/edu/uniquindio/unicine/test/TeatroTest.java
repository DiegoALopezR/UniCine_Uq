package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Administrador;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

public class TeatroTest
{
    @Test
    @Sql("classpath:dataset.sql")
    public void crearTeatros() throws Exception {
        try {
            Ciudad ciudad = new Ciudad("Pereira");
            Administrador administrador = new Administrador(1234,"felipe","felipe@gmail.com","0991");
            Teatro teatro = new Teatro("Norte centenario",ciudad,administrador);
            teatro.setNombre("CINEC");
            Teatro teatroCreado = adminTeatroServicio.crearTeatros(teatro);
            Assertions.assertNotNull(teatroCreado);
            System.out.println(teatroCreado.getDireccion());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerTeatroTest() throws Exception {
        try {
            Teatro teatroCreado= adminTeatroServicio.obtenerTeatro(3);
            Assertions.assertNotNull(teatroCreado);
            System.out.println(teatroCreado.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarTeatrosTeatro() throws Exception {

        try {
            Teatro teatroGuardado = adminTeatroServicio.obtenerTeatro(4);
            System.out.println(teatroGuardado.getNombre());
            teatroGuardado.setNombre("Gold Cine");
            Teatro teatroActualizado = adminTeatroServicio.actualizarTeatros(teatroGuardado);
            Assertions.assertNotNull(teatroActualizado);
            System.out.println(teatroActualizado.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTeatrosTest() throws Exception {

        try {
            adminTeatroServicio.eliminarTeatros(4);
            Teatro teatro = adminTeatroServicio.obtenerTeatro(4);
            Assertions.assertNull(teatro);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTeatrosTest()  {
        List<Teatro> teatros = adminTeatroServicio.listarTeatros();
        teatros.forEach(System.out::println);
    }

}
