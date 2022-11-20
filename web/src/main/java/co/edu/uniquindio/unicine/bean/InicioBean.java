package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    private AdministradorServicio administradorServicio;
    @Getter @Setter
    private List<Pelicula> peliculasCartelera;
    @Getter @Setter
    private List<Pelicula> peliculasProximamente;
    @Getter @Setter
    private List<Ciudad> ciudades;
    @Getter @Setter
    private Ciudad ciudad;
    @Getter @Setter
    private List<String> listaImagenes;

    @PostConstruct
    public void inicializar(){
        try {
            listaImagenes = new ArrayList<>();
            listaImagenes.add("https://picsum.photos/300/200?random=1");
            listaImagenes.add(" ");
            listaImagenes.add(" ");

            peliculasCartelera = clienteServicio.buscarPeliculaPorEstado(EstadoPelicula.CARTELERA);
            peliculasProximamente = clienteServicio.buscarPeliculaPorEstado(EstadoPelicula.PROXIMAMENTE);
            ciudades = administradorServicio.listarCiudad();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void elegirCiudad(){
        try {
            if(ciudad != null){
                peliculasCartelera = clienteServicio.buscarPeliculaPorEstadoCiudad(ciudad.getCodigoPostal(), EstadoPelicula.CARTELERA);
                peliculasProximamente = clienteServicio.buscarPeliculaPorEstadoCiudad(ciudad.getCodigoPostal(), EstadoPelicula.PROXIMAMENTE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
