package co.edu.uniquindio.unicine.serviceTest;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorTeatroService;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
public class ClienteServicioTest
{
    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private AdministradorTeatroService adminTeatroServicio;

    //---------------------------------  LOGUEARSE -------------------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void loguearse()
    {
        try
        {
            Cliente cliente = clienteServicio.login("anam.cardenas@gmail.com", "1234");
            Assertions.assertEquals("anam.cardenas@gmail.com", cliente.getEmail());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    //----------------------------------- BUSCAR PELICULA ---------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void buscarPeliculaPorNombre ()
    {
        try {
            List<Pelicula> peliculas = clienteServicio.buscarPeliculaPorNombre("La huerfana: EL origen");
            peliculas.forEach(System.out::println);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    //------------------------------------ CRUD DE CLIENTE ----------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void registrarClienteTest()
    {
        Cliente cliente = Cliente.builder().cedula(1234).nombre("Paco").correo("paco@gmail.com").contrasena("3321").imagen_perfil("fotopaco").build();
        try
        {
            Cliente nuevo = clienteServicio.registrarCliente(cliente);
            Assertions.assertNotNull(nuevo);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarClienteTest()
    {

        try {
            Cliente cliente = clienteServicio.obtenerClientePorCedula(1007);
            cliente.setNombreCompleto("Nuevo nombre");
            Cliente nuevoCliente = clienteServicio.actualizarCliente(cliente);

            Assertions.assertEquals("Nuevo nombre", nuevoCliente.getNombreCompleto());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarClienteTest()
    {

        try
        {
            clienteServicio.eliminarCliente(1007);
            Cliente cliente = clienteServicio.obtenerClientePorCedula(1007);
            Assertions.assertNull(cliente);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarClienteTest()
    {
        List<Cliente> listaClientes = clienteServicio.listarClientes();
        listaClientes.forEach(System.out::println);
    }

    //----------------------------------- LISTAR LAS COMPRAS DEL CLIENTE ------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void listarComprasClienteTest()
    {
        try {
            clienteServicio.listarHitorialCompra(1004);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    //----------------------------------- REALIZAR COMPRA ---------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void realizarCompraTest() throws Exception
    {
        //Metodos obtenidos
        Cliente cliente = clienteServicio.obtenerClientePorCedula(1004);
        MedioPago medioPago = MedioPago.DAVIPLATA;
        Cupon cupon = administradorServicio.obtenerCupones(1);
        Funcion funcion = adminTeatroServicio.obtenerFuncion(3);
        Confiteria confiteria = administradorServicio.obtenerConfiteria(1);
        Confiteria confiteria1 = administradorServicio.obtenerConfiteria(3);

        //Realizar
        List<Entrada> entradas = new ArrayList<>();
        Entrada entrada = new Entrada("03", "fila", "colunma");
        Entrada entrada1= new Entrada("05", "fila", "columna");
        entradas.add(entrada);
        entradas.add(entrada1);

        List<CompraConfiteria> compraConfiterias = new ArrayList<>();
        CompraConfiteria compraConfiteria = new CompraConfiteria("01", 12.500, 2);
        CompraConfiteria compraConfiteria1 = new CompraConfiteria("033", 10.200, 1);

        try {
            Compra compra = clienteServicio.hacerCompra(cliente, entradas, compraConfiterias, medioPago, cupon, funcion);
            Assertions.assertNotNull(compra);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void redimirCuponTest()
    {
        try {
            boolean cuponRedimido = clienteServicio.redirCupon(3);
            Assertions.assertTrue(cuponRedimido);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    //------------------------------------CAMBIAR CONTRASEÑA ------------------------------------
    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarPasswordTest()
    {
        try {
            Boolean cambiarContraseña = clienteServicio.cambiarContraseña("anam.cardenas@gmail.com", "1122");
            Assertions.assertTrue(cambiarContraseña);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
