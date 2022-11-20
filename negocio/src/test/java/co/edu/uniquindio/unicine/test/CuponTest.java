package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Cupon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

public class CuponTest
{
    @Test
    @Sql("classpath:dataset.sql")
    public void crearCuponesTest(){
        try {
            Cupon cupon = new Cupon(0.15, LocalDateTime.now().toLocalDate(), "Registro", "bienvenido registro");
            Cupon cuponCreado= administradorServicio.crearCupones(cupon);
            Assertions.assertNotNull(cuponCreado);
            System.out.println(cuponCreado.getCriterio());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCuponesTest() throws Exception {

        try {
            Cupon cupon = administradorServicio.obtenerCupones(5);
            Assertions.assertNotNull(cupon);
            System.out.println(cupon.getCriterio());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarCuponesTest() throws Exception {

        try {
            Cupon cuponGuardado = administradorServicio.obtenerCupones(5);
            System.out.println(cuponGuardado.getCriterio());
            cuponGuardado.setCriterio("Navidad");
            Cupon cuponActualizado= administradorServicio.actualizarCupones(cuponGuardado);
            Assertions.assertNotNull(cuponActualizado);
            System.out.println(cuponActualizado.getCriterio());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCuponesTest() throws Exception {
        try {
            administradorServicio.eliminarCupones(5);
            Cupon cupon= administradorServicio.obtenerCupones(5);
            Assertions.assertNull(cupon);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCuponesTest() {
        List<Cupon> cupones = administradorServicio.listarCupones();
        cupones.forEach(System.out::println);
    }

}



    @Test
    @Sql("classpath:dataset.sql")
    public void loginTest() throws Exception {

        try {
            AdministradorTeatro administradorTeatro = adminTeatroServicio.login("maria@gmail.com","0987");
            Assertions.assertEquals("maria@gmail.com" , administradorTeatro.getCorreo());
        }catch (Exception e){
            throw new Exception(e);
        }
    }

}
