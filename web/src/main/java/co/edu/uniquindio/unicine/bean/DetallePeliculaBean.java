package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class DetallePeliculaBean implements Serializable {

    @Getter @Setter
    private Pelicula pelicula;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Value("#{param['pelicula_id']}")
    private String codigoPelicula;

    public void init(){
        System.out.println(pelicula);
        try{

            if(codigoPelicula != null && !codigoPelicula.isEmpty()){
                pelicula = administradorServicio.obtenerPelicula(Integer.parseInt(codigoPelicula));
                System.out.println(pelicula);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
