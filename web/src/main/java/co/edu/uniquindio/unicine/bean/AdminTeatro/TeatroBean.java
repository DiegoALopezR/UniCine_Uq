package co.edu.uniquindio.unicine.bean.AdminTeatro;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Teatro;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
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
public class TeatroBean implements Serializable {

    @Getter @Setter
    private Teatro teatro;

    @Getter @Setter
    private List<Ciudad> ciudades;
    @Getter @Setter
    private List<AdministradorTeatro> administradorTeatros;

    @Getter
    @Setter
    private List<Teatro> teatros;

    @Getter
    @Setter
    private List<Teatro> teatrosSeleccionados;

    private boolean editar;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Autowired
    private AdministradorServicio administradorServicio;


    @PostConstruct
    public void init(){
        teatro = new Teatro();
        ciudades = administradorServicio.listarCiudad();
        administradorTeatros = administradorServicio.listarAdministradorTeatros();

        teatrosSeleccionados = new ArrayList<>();
        teatros = adminTeatroServicio.listarTeatros();
        editar=false;
    }


    public void registrarTeatro(){
        try {
            if (!editar) {
                teatro.setAdministrador(null);
                Teatro registro = adminTeatroServicio.crearTeatros(teatro);
                teatros.add(teatro);

                teatro = new Teatro();
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_teatro", facesMessage);
            }else{
                adminTeatroServicio.actualizarTeatros(teatro);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "ACTUALIZACION EXITOSA");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_teatro", fm);

            }

        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_teatro", facesMessage);
        }

    }

    public void eliminarTeatros(){

        try {
            for (Teatro teatro : teatrosSeleccionados){
                adminTeatroServicio.eliminarTeatros(teatro.getNit());
                teatros.remove(teatro);
            }
            teatrosSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_teatro", fm);
        }
    }


    public String getMensajeBorrar(){
        if(teatrosSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + teatrosSeleccionados.size() + ")" ;
        }
    }

    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR TEATRO";
        }
        return "CREAR TEATRO" ;
    }


    public void seleccionarTeatro(Teatro teatroSelec){
        this.teatro=teatroSelec;
        editar=true;
    }

    public void crearTeatroDialog(){
        this.teatro= new Teatro();
        editar=false;
    }

}
