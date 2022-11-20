package co.edu.uniquindio.unicine.bean;

import co.edu.uniquindio.unicine.entidades.Administrador;
import co.edu.uniquindio.unicine.entidades.AdministradorTeatro;
import co.edu.uniquindio.unicine.entidades.Ciudad;
import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.AdministradorTeatroService;
import co.edu.uniquindio.unicine.servicios.ClienteServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;


@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Autowired
    private AdministradorTeatroService adminTeatroServicio;

    @Getter
    @Setter
    private boolean autenticado;

    @Getter
    @Setter
    private String email, password;

    @Getter
    @Setter
    private String tipoSesion;

    @Getter
    @Setter
    private Ciudad ciudadSeleccionada;


    @PostConstruct
    public void inicializar() {

        autenticado = false;
    }

    public String iniciarSesionCliente(Cliente Cliente) {

        if (!email.isEmpty() && !password.isEmpty()) {
            try {
                Cliente = clienteServicio.login(email, password);
                tipoSesion = "cliente";
                autenticado = true;

                return "/index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "El correo y la contrasena son obligatorios");
            FacesContext.getCurrentInstance().addMessage("login-bean", fm);
        }
        return null;
    }


    public String iniciarSesionAdmins(Administrador Administrador, AdministradorTeatro AdministradorTeatro) {

        if (!email.isEmpty() && !password.isEmpty()) {
            try {

                Administrador = administradorServicio.login(email, password);

                if (Administrador == null) {
                    AdministradorTeatro = adminTeatroServicio.login(email, password);
                    tipoSesion = "admin_teatro";
                } else {
                    tipoSesion = "admin";
                }
                autenticado = true;

                return "/menuAdministrador?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "El correo y la contrasena son obligatorios");
            FacesContext.getCurrentInstance().addMessage("login-bean", fm);
        }
        return null;
    }


    public void seleccionarCiudad(Ciudad ciudad) {
        this.ciudadSeleccionada = ciudad;
    }


    public String cerrarSesion() {
        String tipo = tipoSesion;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        if (tipo.equals("cliente")) {
            return "/index?faces-redirect=true";
        } else {
            return "/menuAdministrador?faces-redirect=true";
        }
    }

}
