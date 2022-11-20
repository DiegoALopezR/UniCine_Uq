package co.edu.uniquindio.unicine.converter;

import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdministradorTeatroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component
public class TeatroConverter implements Converter<Teatro> {

    @Autowired
   private AdministradorTeatroService adminTeatroServicio;

    @Override
    public Teatro getAsObject(FacesContext context, UIComponent component, String value) {

        Teatro teatro ;
        try {
            teatro = adminTeatroServicio.obtenerTeatro(Integer.parseInt(value));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return teatro;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Teatro value) {

        if(value != null){
            return ""+value.getCodigo();
        }
        return "";
    }
}
