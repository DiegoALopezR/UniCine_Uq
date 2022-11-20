package co.edu.uniquindio.unicine.bean.Administrador;

import co.edu.uniquindio.unicine.entidades.EstadoPelicula;
import co.edu.uniquindio.unicine.entidades.Genero;
import co.edu.uniquindio.unicine.entidades.Pelicula;
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
import java.io.Serializable;
import java.util.*;

@Component
@ViewScoped
public class PeliculaBean implements Serializable {

    @Setter @Getter
    private Pelicula pelicula;

    @Setter @Getter
    private List<Pelicula> peliculas;

    @Setter @Getter
    private List<Pelicula> peliculasSeleccionados;
    private boolean editar;
    @Autowired
    private CloudinaryServicio cloudinaryServicio;
    @Autowired
    private  AdministradorServicio administradorServicio;

    private Map<String, String> imagenes;

    @Setter @Getter
    private List<Genero> generos;

    @Getter @Setter
    private List<EstadoPelicula> estadosPeliculas;

    @Getter @Setter
    private List<EstadoPelicula> estadoPelis;

    @PostConstruct
    public void init(){
        pelicula = new Pelicula();
        peliculas = administradorServicio.listarPeliculas();
        peliculasSeleccionados = new ArrayList<>();
        editar= false;
        imagenes = new HashMap<>();
        generos = Arrays.asList(Genero.values());
        estadosPeliculas = Arrays.asList(EstadoPelicula.values());
        estadoPelis = new ArrayList<>();
    }

    public void registrarPelicula(){
       try {
           if(!editar) {
               if(!imagenes.isEmpty()){

                   pelicula.setEstadoPelicula(estadoPelis.get(0));
                   pelicula.setImagenes(imagenes);
                   Pelicula registro = administradorServicio.crearPeliculas(pelicula);
                   peliculas.add(registro);

                   pelicula = new Pelicula();
                   estadoPelis = new ArrayList<>();

                   FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La pelicula se ha creado exitosamente");
                   FacesContext.getCurrentInstance().addMessage("mensaje_registro_pelicula", facesMessage);

               }else{
                   FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es necesario subir al menos una foto");
                   FacesContext.getCurrentInstance().addMessage("mensaje_pelicula", fm);
               }
           }else{
               administradorServicio.actualizarPeliculas(pelicula);
               FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "La pelicula se ha actualizado exitosamente");
               FacesContext.getCurrentInstance().addMessage("mensaje_registro_pelicula", facesMessage);
           }
       }catch (Exception e){
           FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", e.getMessage());
           FacesContext.getCurrentInstance().addMessage("mensaje_registro_pelicula", facesMessage);
       }
    }



    public void eliminarPeliculas(){

        try {
            for (Pelicula pelicula : peliculasSeleccionados){
                administradorServicio.eliminarPeliculas(pelicula.getCodigo());
                peliculas.remove(pelicula);
            }
            peliculasSeleccionados.clear();
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_eliminar_pelicula", fm);
        }
    }


    public String getMensajeBorrar(){
        if(peliculasSeleccionados.isEmpty()){
            return "Borrar";
        }else{
            return "Borrar (" + peliculasSeleccionados.size() + ")" ;
        }

    }

    public String getMensajeCrearEditar(){
        if(editar){
            return "EDITAR PELICULA";
        }
        return "CREAR PELICULA" ;
    }


    public void seleccionarPelicula(Pelicula peliculaSelec){
        this.pelicula=peliculaSelec;
        editar=true;
    }

    public void crearPeliculaDialog(){
        this.pelicula= new Pelicula();
        editar=false;
    }

    public void subirImagenes(FileUploadEvent event) throws IOException {
        try {
            UploadedFile imagen = event.getFile();
            File imagenFile = convertirUploadedfile(imagen);
            Map resultado = cloudinaryServicio.subirImagen(imagenFile, "peliculas");
            imagenes.put(resultado.get("public_id").toString(), resultado.get("url").toString());
        }catch (Exception e){
            e.printStackTrace();
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("mensaje_pelicula", fm);
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
