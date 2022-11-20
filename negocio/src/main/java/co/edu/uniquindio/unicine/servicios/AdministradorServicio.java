package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;

public interface AdministradorServicio
{
    //-----------------------------LOGIN----------------------------------------------
    Administrador login(String correo, String password) throws Exception;
    //------------------------------METODOS CRUD PARA LA CIUDAD---------------------------
    Ciudad crearCiudad(Ciudad ciudad) throws Exception;

    Ciudad obtenerCiudad(Integer codigo)throws Exception;
    Ciudad actualizarCiudad(Ciudad ciudad) throws Exception;

    void eliminarCiudad(Integer codigo) throws Exception;

    List<Ciudad> listarCiudad();

    //-------------------------METODOS CRUD PARA LOS ADMINISTRADOR_TEATROS--------------------------------
    AdministradorTeatro crearAdministradorTeatros(AdministradorTeatro administradorTeatro) throws Exception;

    AdministradorTeatro obtenerAdministrador(Integer codigo)throws Exception;
    AdministradorTeatro actualizarAdministradorTeatros(AdministradorTeatro administradorTeatro) throws Exception;

    void eliminarAdministradorTeatros(Integer cedula) throws Exception;

    List<AdministradorTeatro> listarAdministradorTeatros();

    //-------------------------METODOS CRUD PARA LOS PELICULAS--------------------------------
    Pelicula crearPeliculas(Pelicula pelicula) throws Exception;

    Pelicula obtenerPelicula(Integer codigo)throws Exception;

    Pelicula actualizarPeliculas(Pelicula pelicula) throws Exception;

    void eliminarPeliculas(Integer codigo) throws Exception;

    List<Pelicula> listarPeliculas();

    //-------------------------METODOS CRUD PARA LOS CONFITERIA--------------------------------
    Confiteria crearConfiteria(Confiteria confiteria);

    Confiteria obtenerConfiteria(Integer codigo)throws Exception;

    Confiteria actualizarConfiteria(Confiteria confiteria) throws Exception;

    void eliminarConfiteria(Integer codigo) throws Exception;

    List<Confiteria> listarConfiteria();
    //------------------------METODOS CRUD PARA LOS CUPONES-----------------------------------
    Cupon crearCupones(Cupon cupon);

    Cupon obtenerCupones(Integer codigo)throws Exception;

    Cupon actualizarCupones(Cupon cupon) throws Exception;

    void eliminarCupones(Integer codigo) throws Exception;

    List<Cupon> listarCupones();


}
