package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.HorarioPelicula;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public class HorarioPeliculaTest
{
    @Test
    @Sql("classpath:dataset.sql")
    public void crearHorariosTest() throws Exception {

        try {
            HorarioPelicula horario = new HorarioPelicula(null, Time.valueOf(LocalTime.now()), LocalDate.now(), LocalDate.now());
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


}
