package co.edu.uniquindio.unicine.bean.AdminTeatro;

import co.edu.uniquindio.unicine.entidades.Horario;
import co.edu.uniquindio.unicine.servicios.AdminTeatroServicio;
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
    private Horario horario;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;


    //-----actualziar-----

    @Getter
    @Setter
    private List<Horario> horarios;

    @Getter
    @Setter
    private List<Horario> horariosSeleccionados;

    @Getter
    @Setter
    private Date fechaHora;
    private boolean editar;

    @PostConstruct
    public void init(){
        horario = new Horario();

        horariosSeleccionados = new ArrayList<>();
        horarios = adminTeatroServicio.listarHorarios();
        editar=false;
    }


    public void registrarHorario(){

        try {
            if(!editar) {
                //horario.setHora(LocalTime.);
                Horario registro = adminTeatroServicio.crearHorarios(horario);
                horarios.add(registro);

                horario = new Horario();

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
            for (Horario horario : horariosSeleccionados){
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


    public void seleccionarHorario(Horario horarioSelec){
        this.horario=horarioSelec;
        editar=true;
    }

    public void crearHorarioDialog(){
        this.horario= new Horario();
        editar=false;
    }


}
