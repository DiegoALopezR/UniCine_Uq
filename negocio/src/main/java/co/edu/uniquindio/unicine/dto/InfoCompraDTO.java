package co.edu.uniquindio.unicine.dto;

import co.edu.uniquindio.unicine.entidades.Funcion;

import java.time.LocalDateTime;

public class InfoCompraDTO
{
    // Esta clase creada con la intencion de poner los atributos requeridos en la consulta de la compra
    private Double precioTotal;
    private LocalDateTime fecha;
    private Funcion funcion;
    private Double precioEntradas;
    private Double precioConfiteria;
}
