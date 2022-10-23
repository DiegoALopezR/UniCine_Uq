package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.repo.ClienteRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteTest
{
    @Autowired
    private ClienteRepo clienteRepo;

    @Test
    public void registar()
    {
        Cliente cliente = new Cliente("123", "JoseGomez", "jose@gmail.com", "joseG");

         Cliente guardado = clienteRepo.save(cliente);

        Assertions.assertEquals("JoseGomez", guardado.getNombreCompleto());

    }

    @Test
    public void eliminar()
    {
        Cliente cliente = new Cliente("123", "JoseGomez", "jose@gmail.com", "joseG");
        cliente.setCedula("1");

        Cliente guardado = clienteRepo.save(cliente);

        clienteRepo.delete(guardado);

        Optional<Cliente> buscado = clienteRepo.findById("4");

        Assertions.assertNull(buscado.orElse(null));


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizar()
    {

        Cliente guardado = clienteRepo.findById("1").orElse(null );

        guardado.setEmail("gomezjose@gmail.com");

        Cliente nuevo = clienteRepo.save(guardado);

        Assertions.assertEquals("gomezjose@gmail.com", nuevo.getEmail());


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtener()
    {

        Optional<Cliente> buscado = clienteRepo.findById("1");

        Assertions.assertNotNull(buscado.orElse(null));

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar()
    {

        List<Cliente> lista = clienteRepo.findAll();

        Assertions.assertNotNull(lista);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPorCorreo()
    {
        Cliente cliente  = clienteRepo.obtener("Ana@email.co");

        Assertions.assertNotNull(cliente);
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void comprobarAutentificacion()
    {
        Cliente cliente = clienteRepo.comprobarAutentificacion("Ana@email.co", "12345");

        Assertions.assertNotNull(cliente);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void paginador()
    {
        List<Cliente> clientes = clienteRepo.findAll(PageRequest.of(0 , 2)).toList();

        clientes.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void paginadorEstado()
    {
        List<Cliente> clientes = clienteRepo.obtenerPorEstado(true , PageRequest.of(0 , 3));

        clientes.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void ordenarRegistros()
    {
        List<Cliente> clientes = clienteRepo.findAll(Sort.by("nombre").descending());

        clientes.forEach(System.out::println);
    }
}

