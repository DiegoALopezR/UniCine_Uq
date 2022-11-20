package co.edu.uniquindio.unicine.serviceTest;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorTeatroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
@Transactional
public class AdministradorTeatroServicioTest
{
    @Autowired
    private AdministradorTeatroService adminTeatroServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    //--------------------------------LOGIN------------------------------------------------

    @Test
    @Sql("classpath:dataset.sql")
    public void loginTest() throws Exception
    {

        try
        {
            AdministradorTeatro administradorTeatro = adminTeatroServicio.login("maria@gmail.com","0987");
            Assertions.assertEquals("maria@gmail.com" , administradorTeatro.getCorreo());
        }catch (Exception e)
        {
            throw new Exception(e);
        }
    }

    //---------------------------------------TEATRO---------------------------------------------------

    @Test
    @Sql("classpath:dataset.sql")
    public void crearTeatros() throws Exception
    {
        try
        {
            Ciudad ciudad = new Ciudad("05", "Armenia");
            Administrador administrador = new Administrador("02","felipe@gmail.com","0991");
            Teatro teatro = new Teatro("01", "direccion", "telefono",Ciudad, AdministradorTeatro );
            teatro.setCodigo("01");
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
            System.out.println(teatroCreado.getCodigo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarTeatrosTeatro() throws Exception
    {

        try {
            Teatro teatroGuardado = adminTeatroServicio.obtenerTeatro(4);
            System.out.println(teatroGuardado.getCodigo());
            teatroGuardado.setCodigo("001");
            Teatro teatroActualizado = adminTeatroServicio.actualizarTeatros(teatroGuardado);
            Assertions.assertNotNull(teatroActualizado);
            System.out.println(teatroActualizado.getCodigo());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarTeatrosTest() throws Exception
    {

        try
        {
            adminTeatroServicio.eliminarTeatros(4);
            Teatro teatro = adminTeatroServicio.obtenerTeatro(4);
            Assertions.assertNull(teatro);
        }catch (Exception e){

            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarTeatrosTest()
    {
        List<Teatro> teatros = adminTeatroServicio.listarTeatros();
        teatros.forEach(System.out::println);
    }

    //-----------------------------------------HORARIO-------------------------------------



    @Test
    @Sql("classpath:dataset.sql")
    public void crearHorariosTest() throws Exception
    {

        try {
            HorarioPelicula horario = new HorarioPelicula("cod", "dia", "hora" , LocalTime.now(), LocalDate.now(), LocalDate.now());
            HorarioPelicula horarioCreado = adminTeatroServicio.crearHorarios(horario);
            Assertions.assertNotNull(horarioCreado);
            System.out.println(horarioCreado.getCodigo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerHorarioTest() throws Exception {

        try {
            HorarioPelicula horarioCreado = adminTeatroServicio.obtenerHorario(3);
            Assertions.assertNotNull(horarioCreado);
            System.out.println(horarioCreado.getCodigo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actuaizarHorariosTest() throws Exception {

        try {
            HorarioPelicula horarioGuardado = adminTeatroServicio.obtenerHorario(3);
            HorarioPelicula horarioOActualziado = adminTeatroServicio.actuaizarHorarios(horarioGuardado);
            Assertions.assertNotNull(horarioGuardado);
            System.out.println(horarioGuardado.getCodigo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarHorarioTest() throws Exception {

        try {
            adminTeatroServicio.eliminarHorario(3);
            HorarioPelicula horario = adminTeatroServicio.obtenerHorario(3);
            Assertions.assertNull(horario);
        }catch (Exception e){
            throw new Exception(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHorariosTest() {

        List<HorarioPelicula> horarios = adminTeatroServicio.listarHorarios();
        horarios.forEach(System.out::println);
    }

    //----------------------------------------FUNCIONES---------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void crearFuncion() throws Exception{

        Pelicula pelicula = administradorServicio.obtenerPelicula(3);
        HorarioPelicula horario = new HorarioPelicula("cod", "dia", "hora" , LocalTime.now(), LocalDate.now(), LocalDate.now());
        Sala sala = adminTeatroServicio.obtenerSala(3);
        Funcion funcion = Funcion.builder().precio(3500.00).pelicula(pelicula).horario(horario).sala(sala).build();

        try {
            Funcion funcionCreada = adminTeatroServicio.crearFuncion(funcion);
            Assertions.assertNotNull(funcionCreada);
            System.out.println(funcionCreada.getCodigo());
            System.out.println(funcionCreada.getSala());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerFuncionTest() throws Exception
    {

        try {
            Funcion funcionCreado = adminTeatroServicio.obtenerFuncion(3);
            Assertions.assertNotNull(funcionCreado);
            System.out.println(funcionCreado.getCodigo());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actuaizarFuncionTest() throws Exception
    {

        try {
            Funcion funcionGuardado = adminTeatroServicio.obtenerFuncion(3);
            System.out.println(funcionGuardado.getCodigo() + " precio : " + funcionGuardado.getPrecio());
            funcionGuardado.setPrecio(12000.00);
            Funcion funcionActualizada = adminTeatroServicio.actuaizarFuncion(funcionGuardado);
            Assertions.assertNotNull(funcionActualizada);
            System.out.println(funcionActualizada.getPrecio());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarFuncionTest() throws Exception
    {

        try {
            adminTeatroServicio.eliminarFuncion(3);
            Funcion funcion = adminTeatroServicio.obtenerFuncion(3);
            Assertions.assertNull(funcion);
        }catch (Exception e)
        {
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarFuncionTest()
    {
        List<Funcion> funciones = adminTeatroServicio.listarFuncion();
        funciones.forEach(System.out::println);
    }


    //-----------------------------------SALAS-------------------------------------

    @Test
    @Sql("classpath:dataset.sql")
    public void crearSalasTest()
    {

        try {
            Ciudad ciudad = new Ciudad("01", "Armenia");
            Administrador administrador = new Administrador("1234","felipe@gmail.com","0991");
            Teatro teatro = new Teatro("Norte centenario","ciudad,administrador", "012", Ciudad, AdministradorTeatro);
            Sala sala = new Sala("012", "nombre", Teatro, DistribucionSillas);
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
    public void obtenerSalaTest() throws Exception
    {

        try
        {
            Sala salaCreada = adminTeatroServicio.obtenerSala(3);
            Assertions.assertNotNull(salaCreada);
            System.out.println(salaCreada.getNombre());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarSalaTest() throws Exception
    {

        try
        {
            Sala salaGuardada = adminTeatroServicio.obtenerSala(3);
            System.out.println(salaGuardada.getNombre());
            salaGuardada.setNombre("Sala 3 RE ULTRAVIP");
            Sala salaActualizada = adminTeatroServicio.actualizarSala(salaGuardada);
            Assertions.assertNotNull(salaActualizada);
            System.out.println(salaActualizada.getNombre());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarSalaTest() throws Exception
    {

        try {
            adminTeatroServicio.eliminarSala(3);
            Sala sala = adminTeatroServicio.obtenerSala(3);
            Assertions.assertNull(sala);
        }catch (Exception e)
        {
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarSalaTest()
    {
        List<Sala> salas = adminTeatroServicio.listarSala();
        salas.forEach(System.out::println);
    }

}
