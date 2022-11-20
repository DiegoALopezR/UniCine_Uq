package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

public class ConfiteriaTest
{
    @Test
    @Sql("classpath:dataset.sql")
    public void crearConfiteria(){

        try {
            Confiteria confiteria = new Confiteria(1234, "ICE" , 7500.00 , "IMG PRODUCTO", TipoConfiteria.Bebidas, null);
            Confiteria ConfiteriaCreada= administradorServicio.crearConfiteria(confiteria);
            Assertions.assertNotNull(ConfiteriaCreada);
            System.out.println(ConfiteriaCreada.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerConfiteriaTest() throws Exception {

        try {
            Confiteria confiteria = administradorServicio.obtenerConfiteria(5);
            Assertions.assertNotNull(confiteria);
            System.out.println(confiteria.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarConfiteriaTest() throws Exception {

        try {
            Confiteria confiteriaGuardo = administradorServicio.obtenerConfiteria(5);
            System.out.println(confiteriaGuardo.getNombre());
            confiteriaGuardo.setNombre("super dog");
            Confiteria confiteriaActualizada= administradorServicio.actualizarConfiteria(confiteriaGuardo);
            Assertions.assertNotNull(confiteriaActualizada);
            System.out.println(confiteriaActualizada.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarConfiteriaTest() throws Exception {

        try {
            administradorServicio.eliminarConfiteria(5);
            Confiteria confiteria= administradorServicio.obtenerConfiteria(5);
            Assertions.assertNull(confiteria);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarConfiteriaTest() {
        List<Confiteria> confiterias = administradorServicio.listarConfiteria();
        confiterias.forEach(System.out::println);
    }

}
