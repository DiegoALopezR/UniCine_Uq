package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServicioImp implements AdministradorServicio
{
    //Este comentrio es pa probar que sirva

    @Autowired
    private final AdministradorRepo administradorRepo;
    private final CiudadRepo ciudadRepo;
    private final TeatroRepo teatroRepo;
    private final CuponRepo cuponRepo;
    private final PeliculaRepo peliculaRepo;
    private final ConfiteriaRepo confiteriaRepo;
    private final AdministradorTeatroRepo administradorTeatroRepo;

    //Constructor
    public AdministradorServicioImp(AdministradorRepo administradorRepo, CiudadRepo ciudadRepo, TeatroRepo teatroRepo, CuponRepo cuponRepo, PeliculaRepo peliculaRepo, ConfiteriaRepo confiteriaRepo, AdministradorTeatroRepo administradorTeatroRepo)
    {
        this.administradorRepo = administradorRepo;
        this.ciudadRepo = ciudadRepo;
        this.teatroRepo = teatroRepo;
        this.cuponRepo = cuponRepo;
        this.peliculaRepo = peliculaRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.administradorTeatroRepo = administradorTeatroRepo;
    }

    //------------------------------------ LOGIN ----------------------------------------------
    /**
     * @Param String correo, String password
     * @Return administrador
     * Este metodo se registra un administrador, el cual primero se validad su autenticacion
     */

    @Override
    public Administrador login(String correo, String password) throws Exception
    {
        Administrador administrador = administradorRepo.comprobarAutenticacion(correo, password);
        return administrador;
    }

    //-------------------------------- CRUD DE CIUDAD ---------------------------------
    /**
     * @Param Ciudad ciudad
     * @Return ciudad
     * Este metodo se crea una ciudad
     */
    @Override
    public Ciudad crearCiudad(Ciudad ciudad) throws Exception
    {

        Ciudad ciudadExiste = ciudadRepo.findById(ciudad.getCodigo()).orElse(null);
        if(ciudadExiste != null){
            throw new Exception("Ya existe la ciudad con ese codigo postal");
        }
        return ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad obtenerCiudad(Integer codigo) throws Exception {
        return null;
    }

    /**
     * @Param Integer codigo
     * @Return ciudad
     * Este metodo se obtiene un ciudad, la cual se manda a buscar a las consultas para validar su existencia
     */
    @Override
    public Ciudad obtenerCiudad(String codigo) throws Exception
    {

        Optional<Ciudad> ciudad = ciudadRepo.findById(codigo);
        if(ciudad.isEmpty()){
            throw new Exception("No existe la ciudad con ese codigo postal");
        }
        return ciudad.get();
    }

    //Se validad la existencia de una ciudad dado el nombre de una ciudad
    public boolean ciudadRepetida(String nombreCiudad)
    {
        return ciudadRepo.findByNombreCiudad(nombreCiudad).orElse(null)!= null;
    }

    /**
     * @Param Ciudad ciudad
     * @Return ciudad
     * Este metodo se actualiza una ciudad la cual primero valida si existe
     */
    @Override
    public Ciudad actualizarCiudad(Ciudad ciudad) throws Exception
    {

        Optional<Ciudad> ciudadGuardado = ciudadRepo.findById(ciudad.getCodigo());
        if(ciudadGuardado.isEmpty()){
            throw new Exception("La ciudad NO EXISTE");
        }
        return ciudadRepo.save(ciudad);
    }

    @Override
    public void eliminarCiudad(Integer codigo) throws Exception {

    }

    /**
     * @Param Integer codigo
     * @Return
     * Este metodo se elimina una ciudad si pasa la validacion
     */
    @Override
    public void eliminarCiudad(String codigo) throws Exception
    {

        Optional<Ciudad> ciudadGuardado = ciudadRepo.findById(codigo);
        if(ciudadGuardado.isEmpty()){
            throw new Exception("La ciudad NO EXISTE");
        }
        ciudadRepo.delete(ciudadGuardado.get());
    }

    //Se lista toda las ciudades
    @Override
    public List<Ciudad> listarCiudad()
    {
        return ciudadRepo.findAll();
    }

    //------------------------------ CRUD DE ADMINISTRADOR_TEATROS --------------------------------------------
    /**
     * @Param AdministradorTeatro administradorTeatro
     * @Return AdministradorTeatro
     * Este metodo se crea un AdministradorTeatro el cual tiene que pasar por unas validaciones antes de crear o guardar
     * el administrador
     */
    @Override
    public AdministradorTeatro crearAdministradorTeatros(AdministradorTeatro administradorTeatro) throws Exception
    {

        boolean administradorTeatrosExiste = AdministradorRepetido(Integer.valueOf(administradorTeatro.getCodigo()));

        if(administradorTeatrosExiste)
        {
            throw new Exception("La cedula para el administrador ya Existe");
        }
        boolean administradorTeatrosExisteCorreo = AdministradorRepetidoCorreo(administradorTeatro.getCorreo()) ;
        if(administradorTeatrosExisteCorreo)
        {
            throw new Exception("El correo para el administrador ya Existe");
        }
        return administradorTeatroRepo.save(administradorTeatro);
    }

    @Override
    public AdministradorTeatro obtenerAdministrador(String s, Integer codigo) throws Exception {
        return null;
    }

    /**
     * @Param Integer codigo
     * @Return ciudad
     * Este metodo se obtiene un administradorTeatro, la cual se manda a buscar a las consultas para validar su existencia
     */
    @Override
    public AdministradorTeatro obtenerAdministrador(String codigo) throws Exception
    {
        Optional<AdministradorTeatro> administradorTeatro = administradorTeatroRepo.findById(codigo);
        if(administradorTeatro.isEmpty())
        {
            throw new Exception("No existe un administrador de teatros con ese codigo");
        }
        return administradorTeatro.get();
    }


    private boolean AdministradorRepetidoCorreo (String correo)
    {
        return administradorTeatroRepo.findByCorreo(correo).equals(correo);
    }
    private boolean AdministradorRepetido(Integer codigo){
        return administradorTeatroRepo.findByCodigo(codigo).orElse(null)!=null;
    }

    /**
     * @Param AdministradorTeatro administradorTeatro
     * @Return AdministradorTeatro
     * Este metodo se actualiza una  administradorTeatro la cual primero valida si existe
     */
    @Override
    public AdministradorTeatro actualizarAdministradorTeatros(AdministradorTeatro administradorTeatro) throws Exception
    {

        Optional<AdministradorTeatro> administradorGuardado = administradorTeatroRepo.findByCodigo(administradorTeatro.getCodigo());
        if(administradorGuardado.isEmpty())
        {
            throw new Exception("El administrador NO EXISTE");
        }
        return administradorTeatroRepo.save(administradorTeatro);
    }

    @Override
    public void eliminarAdministradorTeatros(Integer cedula) throws Exception {

    }

    /**
     * @Param Integer codigo
     * @Return
     * Este metodo se elimina una administradorTeatro si pasa la validacion
     */
    @Override
    public void eliminarAdministradorTeatros(String cedula) throws Exception
    {

        Optional<AdministradorTeatro> administradorGuardado = administradorTeatroRepo.findById(cedula);
        if(administradorGuardado.isEmpty())
        {
            throw new Exception("El administrador NO EXISTE");
        }
        administradorTeatroRepo.delete(administradorGuardado.get());
    }

    //Se lista todos los administradoresTeatros
    @Override
    public List<AdministradorTeatro> listarAdministradorTeatros()
    {
        return administradorTeatroRepo.findAll();
    }

    //----------------------------------------- CRUD DE PELICULAS
    /**
     * @Param Pelicula pelicula
     * @Return pelicula
     * Este metodo se crea una pelicula
     */
    @Override
    public Pelicula crearPeliculas(Pelicula pelicula) throws Exception
    {
        return peliculaRepo.save(pelicula);
    }

    @Override
    public Pelicula obtenerPelicula(Integer codigo) throws Exception {
        return null;
    }

    /**
     * @Param Integer codigo
     * @Return ciudad
     * Este metodo se obtiene una pelicula, la cual se manda a buscar a las consultas para validar su existencia
     */
    @Override
    public Pelicula obtenerPelicula(String codigo) throws Exception
    {
        Optional<Pelicula> pelicula = peliculaRepo.findById(codigo);
        if(pelicula.isEmpty())
        {
            throw new Exception("No existe la pelicula con ese codigo");
        }
        return pelicula.get();
    }

    /**
     * @Param Pelicula pelicula
     * @Return Pelicula
     * Este metodo se actualiza una  pelicula la cual primero valida si existe
     */
    @Override
    public Pelicula actualizarPeliculas(Pelicula pelicula) throws Exception
    {

        Optional<Pelicula> peliculaGuardada = peliculaRepo.findById(pelicula.getCodigo());
        if (peliculaGuardada.isEmpty())
        {
            throw new Exception("La pelicula NO EXISTE");
        }
        return peliculaRepo.save(pelicula);
    }

    @Override
    public void eliminarPeliculas(Integer codigo) throws Exception {

    }

    /**
     * @Param Integer codigo
     * @Return
     * Este metodo se elimina una peliculas si pasa la validacion
     */
    @Override
    public void eliminarPeliculas(String codigo) throws Exception
    {

        Optional<Pelicula> peliculaGuardada = peliculaRepo.findById(codigo);
        if (peliculaGuardada.isEmpty())
        {
            throw new Exception("La pelicula NO EXISTE");
        }
        peliculaRepo.delete(peliculaGuardada.get());
    }

    //Se lista todos las peliculas
    @Override
    public List<Pelicula> listarPeliculas()

    {
        return peliculaRepo.findAll();
    }

    //---------------------------------- CRUD DE CONFITERIA -------------------------------------
    /**
     * @Param Confiteria confiteria
     * @Return confiteria
     * Este metodo se crea una confiteria
     */
    @Override
    public Confiteria crearConfiteria(Confiteria confiteria)
    {
        return confiteriaRepo.save(confiteria);
    }

    @Override
    public Confiteria obtenerConfiteria(Integer codigo) throws Exception {
        return null;
    }

    /**
     * @Param Integer codigo
     * @Return ciudad
     * Este metodo se obtiene un confiteria, la cual se manda a buscar a las consultas para validar su existencia
     */
    @Override
    public Confiteria obtenerConfiteria(String codigo) throws Exception
    {
        Optional<Confiteria> confiteria = confiteriaRepo.findById(codigo);
        if(confiteria.isEmpty()){
            throw new Exception("No existe la confiteria con ese codigo");
        }
        return confiteria.get();
    }

    /**
     * @Param Confiteria confiteria
     * @Return Confiteria
     * Este metodo se actualiza una  confiteria la cual primero valida si existe
     */
    @Override
    public Confiteria actualizarConfiteria(Confiteria confiteria) throws Exception
    {

        Optional<Confiteria> confiteriaGuardada = confiteriaRepo.findById(confiteria.getCodigo());
        if (confiteriaGuardada.isEmpty())
        {
            throw new Exception("El producto NO EXISTE");
        }
        return confiteriaRepo.save(confiteria);
    }

    @Override
    public void eliminarConfiteria(Integer codigo) throws Exception {

    }

    /**
     * @Param Integer codigo
     * @Return
     * Este metodo se elimina una confiteria si pasa la validacion
     */
    @Override
    public void eliminarConfiteria(String codigo) throws Exception
    {

        Optional<Confiteria> confiteriaGuardada = confiteriaRepo.findById(codigo);
        if (confiteriaGuardada.isEmpty())
        {
            throw new Exception("El producto NO EXISTE");
        }
        confiteriaRepo.delete(confiteriaGuardada.get());
    }

    //Se lista todos las confiterias
    @Override
    public List<Confiteria> listarConfiteria()

    {
        return confiteriaRepo.findAll();
    }


    //------------------------------- CRUD DE CUPONES ------------------------------------
    /**
     * @Param Cupon cupon
     * @Return cupon
     * Este metodo se crea una cupon
     */
    @Override
    public Cupon crearCupones(Cupon cupon)
    {
        return cuponRepo.save(cupon);
    }

    @Override
    public Cupon obtenerCupones(Integer codigo) throws Exception {
        return null;
    }

    /**
     * @Param Integer codigo
     * @Return ciudad
     * Este metodo se obtiene un cupones, la cual se manda a buscar a las consultas para validar su existencia
     */
    @Override
    public Cupon obtenerCupones(String codigo) throws Exception
    {
        Optional<Cupon> cupon = cuponRepo.findById(codigo);
        if(cupon.isEmpty())
        {
            throw new Exception("No existe el cupon con ese codigo postal");
        }
        return cupon.get();
    }

    /**
     * @Param Cupon cupon
     * @Return Cupon
     * Este metodo se actualiza una  cupon la cual primero valida si existe
     */
    @Override
    public Cupon actualizarCupones(Cupon cupon) throws Exception
    {

        Optional<Cupon> cuponGuardado = cuponRepo.findById(cupon.getCodigo());
        if(cuponGuardado.isEmpty())
        {
            throw new Exception("El cupon NO EXISTE");
        }
        return cuponRepo.save(cupon);
    }

    @Override
    public void eliminarCupones(Integer codigo) throws Exception {

    }

    /**
     * @Param Integer codigo
     * @Return
     * Este metodo se elimina una cupon si pasa la validacion
     */
    @Override
    public void eliminarCupones(String codigo) throws Exception
    {
        Optional<Cupon> cuponGuardado = cuponRepo.findById(codigo);
        if(cuponGuardado.isEmpty())
        {
            throw new Exception("El cupon NO EXISTE");
        }
        cuponRepo.delete(cuponGuardado.get());
    }

    //Se lista todos los cupones
    @Override
    public List<Cupon> listarCupones()

    {
        return cuponRepo.findAll();
    }
}
