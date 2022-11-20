package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Pelicula;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

public class PeliculaTest
{
    @Test
    @Sql("classpath:dataset.sql")
    public void crearPeliculas(){
        try {
            Pelicula pelicula = new Pelicula("La purga 2", "trailer de la peli" , "img " , " sipnisis peli" , "reparto de la peli", true, null);
            Pelicula peliculaCreada= administradorServicio.crearPeliculas(pelicula);
            Assertions.assertNotNull(peliculaCreada);
            System.out.println(peliculaCreada.getNombrePelicula());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPeliculaTest() throws Exception {

        try {
            Pelicula pelicula = administradorServicio.obtenerPelicula(5);
            Assertions.assertNotNull(pelicula);
            System.out.println(pelicula.getNombrePelicula());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarPeliculasTest() throws Exception {

        try {
            Pelicula peliculaGuardado = administradorServicio.obtenerPelicula(5);
            System.out.println(peliculaGuardado.getNombrePelicula());
            peliculaGuardado.setNombrePelicula("son como ninos");
            Pelicula peliculaActualizada= administradorServicio.actualizarPeliculas(peliculaGuardado);
            Assertions.assertNotNull(peliculaActualizada);
            System.out.println(peliculaActualizada.getNombrePelicula());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarPeliculasTest() throws Exception {

        try {
            administradorServicio.eliminarPeliculas(5);
            Pelicula pelicula= administradorServicio.obtenerPelicula(5);
            Assertions.assertNull(pelicula);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPeliculas() {
        List<Pelicula> peliculas = administradorServicio.listarPeliculas();
        peliculas.forEach(System.out::println);
    }



}
