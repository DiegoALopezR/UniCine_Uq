package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.dto.PeliculaFuncion;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {

    @Setter @Getter
    private String busqueda;

    @Autowired
    private ClienteServicio clienteServicio;

    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Getter  @Setter
    private List<PeliculaFuncion> peliculas;

    @PostConstruct
    public void init(){
        if(busquedaParam != null && !busquedaParam.isEmpty()){
            peliculas = clienteServicio.listarPeliculasFunciones(busquedaParam);
        }
    }

    public String buscar(){
        if(!busqueda.isEmpty()){
            return "resultadoBusqueda?faces-redirect=true&amp;busqueda="+busqueda;
        }
        return "";
    }
}
