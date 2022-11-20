package co.edu.uniquindio.unicine.converter;

import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component
public class PeliculaConverter implements Converter<Pelicula> {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Override
    public Pelicula getAsObject(FacesContext context, UIComponent component, String value) {
        Pelicula pelicula ;
        try {
            pelicula = administradorServicio.obtenerPelicula(Integer.parseInt(value));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return pelicula;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Pelicula value) {
        if(value != null){
            return ""+value.getCodigo();
        }
        return "";
    }
}
