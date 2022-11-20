package co.edu.uniquindio.unicine.bean.AdminTeatro;

import co.edu.uniquindio.unicine.entidades.HorarioPelicula;
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
import java.util.Date;
import java.util.List;

@Component
@ViewScoped
public class HorarioBean implements Serializable {

    @Getter
    @Setter
    private HorarioPelicula horario;

    @Autowired
    private AdministradorTeatroService adminTeatroServicio;


    //-----actualziar-----

    @Getter
    @Setter
    private List<HorarioPelicula> horarios;

    @Getter
    @Setter
    private List<HorarioPelicula> horariosSeleccionados;

    @Getter
    @Setter
    private Date fechaHora;
    private boolean editar;

    @PostConstruct
    public void init(){
        horario = new HorarioPelicula();

        horariosSeleccionados = new ArrayList<>();
        horarios = adminTeatroServicio.listarHorarios();
        editar=false;
    }


    public void registrarHorario(){

        try {
            if(!editar) {
                //horario.setHora(LocalTime.);
                HorarioPelicula registro = adminTeatroServicio.crearHorarios(horario);
                horarios.add(registro);

                horario = new HorarioPelicula();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "REGISTRO EXITOSO");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_horario", fm);
            }else{

                adminTeatroServicio.actuaizarHorarios(horario);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "ACTUALIZACION EXITOSA");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_horario", fm);
            }

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_horario", fm);
            throw new RuntimeException(e);
        }
    }


    public void eliminarHorarios(){

        try {
            for (HorarioPelicula horario : horariosSeleccionados){
                adminTeatroServicio.eliminarHorario(horario.getCodigo());
                horarios.remove(horario);
            }
            horariosSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_horarios", fm);
        }
    }

    public String getMensajeBorrar(){
        if(horariosSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + horariosSeleccionados.size() + ")" ;
        }

    }


    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR HORARIO";
        }
        return "CREAR HORARIO" ;
    }


    public void seleccionarHorario(HorarioPelicula horarioSelec){
        this.horario=horarioSelec;
        editar=true;
    }

    public void crearHorarioDialog(){
        this.horario= new HorarioPelicula();
        editar=false;
    }


}
