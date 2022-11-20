package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;

import java.util.List;

public interface AdministradorTeatroService
{

    //------------------------------LOGIN-----------------------------------------------
    AdministradorTeatro login(String correo, String password) throws Exception;

    //-------------------------METODOS CRUD PARA LOS TEATROS--------------------------------
    Teatro crearTeatros(Teatro teatro) throws Exception;

    Teatro obtenerTeatro(Integer codigo) throws Exception;

    Teatro actualizarTeatros(Teatro teatro) throws Exception;

    void eliminarTeatros(Integer codigo) throws Exception;

    List<Teatro> listarTeatros();

    //------------------------------CRUD DE HORARIOS------------------------------------
    HorarioPelicula crearHorarios(HorarioPelicula horario)throws Exception;

    HorarioPelicula obtenerHorario(Integer codigo)throws Exception;
    HorarioPelicula actuaizarHorarios(HorarioPelicula horario)throws Exception;

    void eliminarHorario(Integer codigo)throws Exception;

    List<HorarioPelicula> listarHorarios();

    //----------------------------CRUD DE FUNCIONES------------------------------------------
    Funcion crearFuncion(Funcion funcion)throws Exception;
    Funcion obtenerFuncion(Integer codigo)throws Exception;
    Funcion actuaizarFuncion(Funcion funcion)throws Exception;

    void eliminarFuncion(Integer codigo)throws Exception;

    List<Funcion> listarFuncion();

    //------------------------------ CRUD DE SALAS ---------------------------------------------
    Sala crearSalas(Sala sala);
    Sala obtenerSala(Integer codigo)throws Exception;
    Sala actualizarSala(Sala sala)throws Exception;
    void eliminarSala(Integer codigoSala)throws Exception;
    List<Sala> listarSala();
}
