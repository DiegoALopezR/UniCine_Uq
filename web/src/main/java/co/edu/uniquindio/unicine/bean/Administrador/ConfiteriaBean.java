package co.edu.uniquindio.unicine.bean.Administrador;

import co.edu.uniquindio.unicine.entidades.Confiteria;
import co.edu.uniquindio.unicine.entidades.TipoConfiteria;
import co.edu.uniquindio.unicine.servicios.AdministradorServicio;
import co.edu.uniquindio.unicine.servicios.CloudinaryServicio;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Component
@ViewScoped
public class ConfiteriaBean {

    @Getter @Setter
    private Confiteria confiteria;

    @Getter @Setter
    private TipoConfiteria tipoConfiteria;

    @Getter @Setter
    private List<Confiteria> confiterias;

    @Getter @Setter
    private List<Confiteria> confiteriasSeleccionados;

    private boolean editar;

    @Autowired
    private AdministradorServicio administradorServicio;

    @Getter @Setter
    private List<TipoConfiteria> tipoConfiterias;

    @Getter @Setter
    private List<TipoConfiteria> tipoConfi;

    @Autowired
    private CloudinaryServicio cloudinaryServicio;

    private Map<String, String> imagenes;

    @PostConstruct
    public void init(){
        confiteria = new Confiteria();

        confiterias = administradorServicio.listarConfiteria();
        confiteriasSeleccionados = new ArrayList<>();
        editar= false;
        tipoConfiterias= Arrays.asList(TipoConfiteria.values());
        tipoConfi = new ArrayList<>();
        imagenes = new HashMap<>();
    }

    public void registrarConfiteria(){

        try {
            if(!editar) {
                if(!imagenes.isEmpty()){
                confiteria.setTipoConfiteria(tipoConfi.get(0));
                confiteria.setImagenes(imagenes);
                Confiteria registro = administradorServicio.crearConfiteria(confiteria);
                confiterias.add(registro);

                confiteria = new Confiteria();
                tipoConfi = new ArrayList<>();
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro Exitoso");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_confiteria", facesMessage);
                }else{
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es necesario subir al menos una foto");
                    FacesContext.getCurrentInstance().addMessage("mensaje_registro_confiteria", fm);
                }
            }else{
                administradorServicio.actualizarConfiteria(confiteria);
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Actualizacion Exitosa");
                FacesContext.getCurrentInstance().addMessage("mensaje_registro_confiteria", facesMessage);
            }
        }catch (Exception e){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_confiteria", facesMessage);
        }
    }



    public void eliminarConfiterias(){

        try {
            for (Confiteria confiteria : confiteriasSeleccionados){
                administradorServicio.eliminarConfiteria(confiteria.getCodigoProducto());
                confiterias.remove(confiteria);
            }
            confiteriasSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_confiteria", fm);
        }
    }


    public String getMensajeBorrar(){
        if(confiteriasSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + confiteriasSeleccionados.size() + ")" ;
        }

    }

    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR CONFITERIA";
        }
        return "CREAR CONFITERIA" ;
    }


    public void seleccionarConfiteria(Confiteria confiteriaSelec){
        this.confiteria=confiteriaSelec;
        editar=true;
    }

    public void crearConfiteriaDialog(){
        this.confiteria= new Confiteria();
        editar=false;
    }



    public void subirImagenes(FileUploadEvent event) throws IOException {
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedfile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "confiterias");
            imagenes.put(resultado.get("public_id").toString(), resultado.get("url").toString());
        }catch (Exception e){
            e.printStackTrace();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_registro_confiteria", fm);
        }
    }

    private File convertirUploadedfile(UploadedFile imagen) throws IOException{
        File file = new File(imagen.getFileName());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getContent());
        fos.close();
        return file;
    }
}
