package co.edu.uniquindio.unicine.bean.AdminTeatro;

import co.edu.uniquindio.unicine.entidades.Sala;
import co.edu.uniquindio.unicine.entidades.Teatro;
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
import java.util.List;

@Component
@ViewScoped
public class SalaBean implements Serializable {

    @Getter
    @Setter
    private Sala sala;

    @Autowired
    private AdminTeatroServicio adminTeatroServicio;

    @Getter
    @Setter
    private List<Sala> salas;
    @Getter
    @Setter
    private List<Sala> salasSeleccionadas;

    private boolean editar;

    @Getter @Setter
    private List<Teatro> teatros;

    @PostConstruct
    public void init(){

        sala = new Sala();

        teatros = adminTeatroServicio.listarTeatros();

        salas = adminTeatroServicio.listarSala();
        salasSeleccionadas = new ArrayList<>();
        editar = false;

    }



    public void registrarSala(){
        try {
            if(!editar) {
                Sala registro = adminTeatroServicio.crearSalas(sala);
                salas.add(registro);

                sala = new Sala();

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "REGISTRO EXITOSO");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_sala", fm);
            }else{

                adminTeatroServicio.actualizarSala(sala);
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "ACTUALIZACION EXITOSA");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_sala", fm);
            }

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_sala", fm);
            throw new RuntimeException(e);
        }
    }

    public void eliminarSalas(){

        try {
            for (Sala sala : salasSeleccionadas){
                adminTeatroServicio.eliminarSala(sala.getNumeroSala());
                salas.remove(sala);
            }
            salasSeleccionadas.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_sala", fm);
        }
    }

    public String getMensajeBorrar(){
        if(salasSeleccionadas.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + salasSeleccionadas.size() + ")" ;
        }

    }

    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR SALA";
        }
        return "CREAR SALA" ;
    }


    public void seleccionarSala(Sala salaSelec){
        this.sala=salaSelec;
        editar=true;
    }

    public void crearSalaDialog(){
        this.sala= new Sala();
        editar=false;
    }

}
