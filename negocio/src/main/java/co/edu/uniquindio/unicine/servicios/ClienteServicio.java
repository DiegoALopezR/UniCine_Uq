package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.dto.PeliculaFuncion;
import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;

public interface ClienteServicio
{

    //--------------------------------- LOGIN --------------------------------------
    Cliente login(String correo, String password) throws Exception;

    //--------------------------------- Buscar Pelicula --------------------------------------
    List<Pelicula> buscarPeliculaPorNombre (String nombre) throws Exception;
    List<Pelicula> buscarPeliculaPorEstadoCiudad (Integer codigo, String estadoPelicula)  throws Exception;
    List<Pelicula> buscarPeliculaPorEstado (String estadoPelicula)  throws Exception;
    //-------------------------------- CRUD DE CLIENTE ----------------------------
    Cliente registrarCliente(Cliente cliente) throws Exception;
    Cliente obtenerClientePorCedula(Integer cedula) throws Exception;
    Cliente actualizarCliente(Cliente cliente) throws Exception;

    void eliminarCliente(Integer codigoCliente) throws Exception;

    List<Cliente> listarClientes();

    //-------------------------------- LISTAR LAS PROPIAS COMPRAS ---------------------
    void listarHitorialCompra(Integer cedulaCliente) throws Exception;

    //-------------------------------- REALIZAR COMPRA ---------------------------------
    Compra hacerCompra(Cliente cliente, List<Entrada> entradas, List<CompraConfiteria> compraConfiteria, MedioPago medioPago, Cupon cupon, Funcion funcion) throws Exception;

    //-------------------------------- REDIMIR CUPONES --------------------------------
    boolean redirCupon(Integer codigoCupon) throws Exception;

    //-------------------------------- Cambiar Contraseña ----------------------------
    boolean cambiarContraseña(String correo, String passwordNueva)throws Exception;

    //------------------------------- Metodos de Calificacion -----------------------------------
    Calificacion asignarCalificacion(Calificacion calificacion) throws Exception;

    Double promedioPelicula (Pelicula pelicula);

    //-------------------------------- Metodos de PQRS -------------------------------------------
    Pqrs crearPqrs(Pqrs pqrs) throws Exception;

    void listarPqrs(String correo)throws Exception;

    //-------------------------------- Metodo filtrar ---------------------------------------------
    List<PeliculaFuncion> listarPeliculasFunciones(String nombre);

}
