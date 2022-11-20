package co.edu.uniquindio.unicine.bean.AdminTeatro;

import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.entidades.HorarioPelicula;
import co.edu.uniquindio.unicine.entidades.Pelicula;
import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorTeatroService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class FuncionBean implements Serializable {

    @Getter
    @Setter
    private Funcion funcion;

    @Getter
    @Setter
    private List<Funcion> funciones;

    @Getter
    @Setter
    private List<Funcion> funcionesSeleccionadas;

    @Autowired
    private AdministradorTeatroService adminTeatroServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    private boolean editar;


    @Getter
    @Setter
    private List<Sala> salas;

    @Getter
    @Setter
    private List<Pelicula> peliculas;

    @Getter
    @Setter
    private List<HorarioPelicula> horarios;

    @PostConstruct
    public void init(){
        funcion = new Funcion();

        salas= adminTeatroServicio.listarSala();
        horarios= adminTeatroServicio.listarHorarios();
        peliculas = administradorServicio.listarPeliculas();

        funcionesSeleccionadas = new ArrayList<>();
        funciones = adminTeatroServicio.listarFuncion();
        editar=false;
    }


    public void registrarFuncion(){
        try {
            if(!editar) {
                Funcion registro = adminTeatroServicio.crearFuncion(funcion);
                funciones.add(registro);

                funcion = new Funcion();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "REGISTRO EXITOSO");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_funcion", fm);
            }else{

                adminTeatroServicio.actuaizarFuncion(funcion);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "ACTUALIZACION EXITOSA");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_funcion", fm);
            }

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_funcion", fm);
            throw new RuntimeException(e);
        }
    }


    public void eliminarFunciones(){
        try {
            for (Funcion funcion : funcionesSeleccionadas){
                adminTeatroServicio.eliminarFuncion(funcion.getCodigo());
                funciones.remove(funcion);
            }
           funcionesSeleccionadas.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_funcion", fm);
        }
    }

    public String getMensajeBorrar(){
        if(funcionesSeleccionadas.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + funcionesSeleccionadas.size() + ")" ;
        }

    }

    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR FUNCION";
        }
        return "CREAR FUNCION" ;
    }


    public void seleccionarFuncion(Funcion funcionSelec){
        this.funcion=funcionSelec;
        editar=true;
    }

    public void crearFuncionDialog(){
        this.funcion= new Funcion();
        editar=false;
    }
}
