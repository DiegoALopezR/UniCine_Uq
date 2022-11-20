package co.edu.uniquindio.unicine.bean.Administrador;

import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class AdminTeatroBean {

    @Getter
    @Setter
    private AdministradorTeatro administradorTeatro;

    @Getter
    @Setter
    private List<AdministradorTeatro> adminTeatros;

    @Getter
    @Setter
    private List<AdministradorTeatro> adminTeatrosSeleccionados;

    private boolean editar;

    @Autowired
    private AdministradorServicio administradorServicio;

    @PostConstruct
    public void init(){
        administradorTeatro = new AdministradorTeatro();

        adminTeatrosSeleccionados = new ArrayList<>();
        adminTeatros = administradorServicio.listarAdministradorTeatros();
        editar = false;
    }

    public void registrarAdminTeatro(){

        try {

            if(!editar) {

                AdministradorTeatro registro = administradorServicio.crearAdministradorTeatros(administradorTeatro);
                adminTeatros.add(registro);

                administradorTeatro = new AdministradorTeatro();
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_adminTeatro", facesMessage);
            }else {

                administradorServicio.actualizarAdministradorTeatros(administradorTeatro);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Actualizacion Exitosa");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_adminTeatro", facesMessage);
            }
        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_adminTeatro", facesMessage);
        }
    }


    public void eliminarAdminTeatros(){

        try {
            for (AdministradorTeatro adminTeatro : adminTeatrosSeleccionados){
                administradorServicio.eliminarAdministradorTeatros(adminTeatro.getCodigo());
                adminTeatros.remove(adminTeatro);
            }
            adminTeatrosSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_adminTeatros", fm);
        }
    }


    public String getMensajeBorrar(){
        if(adminTeatrosSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + adminTeatrosSeleccionados.size() + ")" ;
        }

    }

    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR ADMINISTRADOR TEATRO";
        }
        return "CREAR ADMINISTRADOR TEATRO" ;
    }


    public void seleccionarAdminTeatros(AdministradorTeatro adminTeatroSelec){
        this.administradorTeatro =adminTeatroSelec;
        editar=true;
    }

    public void crearAdminTeatroDialog(){
        this.administradorTeatro= new AdministradorTeatro();
        editar=false;
    }
}
