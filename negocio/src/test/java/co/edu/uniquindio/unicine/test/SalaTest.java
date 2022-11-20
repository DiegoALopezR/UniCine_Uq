package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

public class SalaTest
{
    @Test
    @Sql("classpath:dataset.sql")
    public void crearSalasTest() {

        try {
            Ciudad ciudad = new Ciudad("Pereira");
            Administrador administrador = new Administrador(1234,"felipe","felipe@gmail.com","0991");
            Teatro teatro = new Teatro("Norte centenario",ciudad,administrador);
            Sala sala = new Sala(teatro,null);
            sala.setNombre("VIP NICE");
            Sala salaCreada = adminTeatroServicio.crearSalas(sala);
            Assertions.assertNotNull(salaCreada);
            System.out.println(salaCreada.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerSalaTest() throws Exception {

        try {
            Sala salaCreada = adminTeatroServicio.obtenerSala(3);
            Assertions.assertNotNull(salaCreada);
            System.out.println(salaCreada.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarSalaTest() throws Exception {

        try {
            Sala salaGuardada = adminTeatroServicio.obtenerSala(3);
            System.out.println(salaGuardada.getNombre());
            salaGuardada.setNombre("Sala 3 RE ULTRAVIP");
            Sala salaActualizada = adminTeatroServicio.actualizarSala(salaGuardada);
            Assertions.assertNotNull(salaActualizada);
            System.out.println(salaActualizada.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarSalaTest() throws Exception {

        try {
            adminTeatroServicio.eliminarSala(3);
            Sala sala = adminTeatroServicio.obtenerSala(3);
            Assertions.assertNull(sala);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSalaTest() {
        List<Sala> salas = adminTeatroServicio.listarSala();
        salas.forEach(System.out::println);
    }

}


    @Test
    @Sql("classpath:dataset.sql")
    public void loguearse(){
        try {
            Cliente cliente = clienteServicio.login("perdomov.j07@gmail.com", "0987");
            Assertions.assertEquals("perdomov.j07@gmail.com", cliente.getCorreo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
