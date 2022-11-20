package co.edu.uniquindio.unicine.bean.Administrador;

import co.edu.uniquindio.unicine.entidades.Ciudad;
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
public class CiudadBean {

    @Getter @Setter
    private Ciudad ciudad;

    @Getter
    @Setter
    private List<Ciudad> ciudades;

    @Getter
    @Setter
    private List<Ciudad> ciudadesSeleccionados;

    @Autowired
    private AdministradorServicio administradorServicio;

    private boolean editar;

    @PostConstruct
    public void init(){
        ciudad = new Ciudad();
        ciudadesSeleccionados = new ArrayList<>();
        ciudades = administradorServicio.listarCiudad();
        editar=false;
    }

    public void registrarCiudad(){

        try {

            if(!editar) {

                Ciudad registro = administradorServicio.crearCiudad(ciudad);
                ciudades.add(registro);
                ciudad = new Ciudad();

                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_ciudad", facesMessage);
            }else{
                administradorServicio.actualizarCiudad(ciudad);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Actualizacion Exitosa");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_ciudad", facesMessage);
            }
        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_ciudad", facesMessage);
        }
    }


    public void eliminarCiudades(){

        try {
            for (Ciudad ciudad : ciudadesSeleccionados){
                administradorServicio.eliminarCiudad(ciudad.getCodigo());
                ciudades.remove(ciudad);
            }
            ciudadesSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_ciudad", fm);
        }
    }


    public String getMensajeBorrar(){
        if(ciudadesSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + ciudadesSeleccionados.size() + ")" ;
        }

    }

    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR CIUDAD";
        }
        return "CREAR CIUDAD" ;
    }


    public void seleccionarCiudad(Ciudad ciudadSelec){
        this.ciudad=ciudadSelec;
        editar=true;
    }

    public void crearCiudadDialog(){
        this.ciudad= new Ciudad();
        editar=false;
    }
}
