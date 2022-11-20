package co.edu.uniquindio.unicine.bean.Administrador;

import co.edu.uniquindio.unicine.entidades.Cupon;
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
public class CuponBean {

    @Getter @Setter
    private Cupon cupon;

    @Getter @Setter
    private List<Cupon> cupones;

    @Getter @Setter
    private List<Cupon> cuponesSeleccionados;

    private boolean editar;

    @Autowired
    private AdministradorServicio administradorServicio;

    @PostConstruct
    public void init(){
        cupon = new Cupon();

        cuponesSeleccionados = new ArrayList<>();
        cupones = administradorServicio.listarCupones();
        editar=false;
    }

    public void registrarCupon(){

        try {
            if (!editar) {
                Cupon registro = administradorServicio.crearCupones(cupon);
                cupones.add(registro);

                cupon= new Cupon();
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_cupon", facesMessage);
            }else{
                administradorServicio.actualizarCupones(cupon);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Actualizacion Exitosa");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_cupon", facesMessage);
            }
        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_cupon", facesMessage);
        }
    }


    public void eliminarCupones(){

        try {
            for (Cupon cupon : cuponesSeleccionados){
                administradorServicio.eliminarCupones(cupon.getCodigo());
                cupones.remove(cupon);
            }
            cuponesSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_cupon", fm);
        }
    }


    public String getMensajeBorrar(){
        if(cuponesSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + cuponesSeleccionados.size() + ")" ;
        }

    }

    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR CUPON";
        }
        return "CREAR CUPON" ;
    }


    public void seleccionarCupon(Cupon cuponSelec){
        this.cupon=cuponSelec;
        editar=true;
    }

    public void crearCuponDialog(){
        this.cupon= new Cupon();
        editar=false;
    }
}
