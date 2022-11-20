package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class ClienteBean implements Serializable {

    //Variables del Bean
    @Setter @Getter
    private Cliente cliente;

    @Setter @Getter
    private String confirmacionPassword;

    @Autowired
    private ClienteServicio clienteServicio;

    //-----------------------------Metodos para verificar el funcionamiento de la pagina-----------------------

    /**
     * Se instancia el cliente, para que no vaya generar la exception de null
     */
    @PostConstruct
    public void instanciarCliente (){
        cliente = new Cliente();
    }


    public void registrarCliente(){
        try {
            if(cliente.getContrasena().equals(confirmacionPassword)){
                clienteServicio.registrarCliente(cliente);

                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta","Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMessage);
            }else{
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta","Contraseñas no coinciden");
                FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMessage);
            }
        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_bean", facesMessage);
        }

    }
}
