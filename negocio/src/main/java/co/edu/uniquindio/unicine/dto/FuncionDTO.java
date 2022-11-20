package co.edu.uniquindio.unicine.dto;

import co.edu.uniquindio.unicine.entidades.HorarioPelicula;
import co.edu.uniquindio.unicine.entidades.Pelicula;

import java.util.Map;

public class FuncionDTO
{

    // Esta clase creada con la intencion de poner los atributos requeridos en la consulta de la funcion
    private String nombrePelicula;
    private String estadoPelicula;
    private Map<String, String> rutaImagen;
    private Integer numeroSala;
    private String direccionTeatro;
    private String nombreCiudad;
    private HorarioPelicula horario;

}
