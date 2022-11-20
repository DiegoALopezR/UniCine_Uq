package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

public class AdministradorTeatroTest
{
    @Test
    @Sql("classpath:dataset.sql")
    public void crearAdministradorTeatrosTest() throws Exception {

        try {
            AdministradorTeatro administradorTeatro = new AdministradorTeatro(1234,"felipe","felipe@gmail.com","0991");
            AdministradorTeatro adminCreado= administradorServicio.crearAdministradorTeatros(administradorTeatro);
            Assertions.assertNotNull(adminCreado);
            System.out.println(adminCreado.getCodigo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerAdministradorTest() throws Exception {
        try {
            AdministradorTeatro administradorTeatro = administradorServicio.obtenerAdministrador(1015);
            Assertions.assertNotNull(administradorTeatro);
            System.out.println(administradorTeatro.getCodigo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarAdministradorTeatrosTest() throws Exception {

        try {
            AdministradorTeatro adminTeatroGuardado = administradorServicio.obtenerAdministrador(1015);
            System.out.println(adminTeatroGuardado.getCodigo());
            adminTeatroGuardado.setCodigo("02");
            AdministradorTeatro adminTeatroActualizado= administradorServicio.actualizarAdministradorTeatros(adminTeatroGuardado);
            Assertions.assertNotNull(adminTeatroActualizado);
            System.out.println(adminTeatroActualizado.getCodigo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarAdministradorTeatrosTest() throws Exception {

        try {
            administradorServicio.eliminarAdministradorTeatros(1015);
            AdministradorTeatro administradorTeatro = administradorServicio.obtenerAdministrador(1015);
            Assertions.assertNull(administradorTeatro);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarAdministradorTeatros() {
        List<AdministradorTeatro> adminisTeatros = administradorServicio.listarAdministradorTeatros();
        adminisTeatros.forEach(System.out::println);
    }


}
