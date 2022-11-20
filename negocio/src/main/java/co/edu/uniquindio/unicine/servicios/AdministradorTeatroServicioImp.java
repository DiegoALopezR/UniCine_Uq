package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public abstract class AdministradorTeatroServicioImp implements AdministradorTeatroService
{

    @Autowired
    private AdministradorTeatroRepo administradorTeatroRepo;
    private final HorarioPeliculaRepo horarioRepo;
    private final TeatroRepo teatroRepo;
    private final SalaRepo salaRepo;
    private final FuncionRepo funcionRepo;
    private final PeliculaRepo peliculaRepo;

    //Constructor
    public AdministradorTeatroServicioImp(HorarioPeliculaRepo horarioRepo, TeatroRepo teatroRepo, SalaRepo salaRepo, FuncionRepo funcionRepo, PeliculaRepo peliculaRepo) {
        this.horarioRepo = horarioRepo;
        this.teatroRepo = teatroRepo;
        this.salaRepo = salaRepo;
        this.funcionRepo = funcionRepo;
        this.peliculaRepo = peliculaRepo;
    }

    //--------------------------------LOGIN------------------------------------------------
    /**
     * @Param String correo, String password
     * @Return administrador
     * Este metodo se registra un administradorTeatro, el cual primero se validad su autenticacion
     */
    @Override
    public AdministradorTeatro login(String correo, String password) throws Exception
    {
        AdministradorTeatro administrador = administradorTeatroRepo.comprobarAutenticacion(correo, password);

        if(administrador == null)
        {
            throw new Exception("Los Datos de Autentificacion son INCORRECTOS");
        }
        return administrador;
    }

    //---------------------------------CRUD DE TEATROS ------------------------------------
    @Override
    public Teatro crearTeatros(Teatro teatro) throws Exception
    {

        boolean teatroExiste = teatroRepetido(teatro.getCodigo());
        if(teatroExiste){
            throw new Exception("El teatro ya Existe");
        }
        return teatroRepo.save(teatro);
    }

    @Override
    public Teatro obtenerTeatro(Integer codigo) throws Exception {
        return null;
    }

    @Override
    public Teatro obtenerTeatro(String codigo) throws Exception
    {
        Optional<Teatro> teatro = teatroRepo.findById(codigo);
        if(teatro.isEmpty())
        {
            throw new Exception("No existe el teatro con ese codigo ");
        }
        return teatro.get();
    }

    public boolean teatroRepetido(String nombreTeatro)
    {
        return teatroRepo.findByNombre(nombreTeatro).orElse(null) != null;
    }

    @Override
    public Teatro actualizarTeatros(Teatro teatro) throws Exception
    {

        Optional<Teatro> teatroGuardado = teatroRepo.findById(teatro.getCodigo());
        if (teatroGuardado.isEmpty())
        {
            throw new Exception("El teatro NO EXISTE");
        }
        return teatroRepo.save(teatro);
    }

    @Override
    public void eliminarTeatros(Integer codigo) throws Exception {

    }

    @Override
    public void eliminarTeatros(String codigo) throws Exception
    {

        Optional<Teatro> teatroGuardado = teatroRepo.findById(codigo);
        if (teatroGuardado.isEmpty())
        {
            throw new Exception("El teatro NO EXISTE");
        }
        teatroRepo.delete(teatroGuardado.get());

    }

    @Override
    public List<Teatro> listarTeatros()
    {
        return teatroRepo.findAll();
    }



    @Override
    public HorarioPelicula obtenerHorario(Integer codigo) throws Exception {
        return null;
    }

    @Override
    public HorarioPelicula crearHorarios2(HorarioPelicula horario) throws Exception {
        return null;
    }

    //---------------------------------CRUD DE HORARIOS-------------------------------------
    @Override
    public HorarioPelicula crearHorarios(HorarioPelicula horario) throws Exception
    {
        boolean horarioExiste = HorarioRepetido(horario.getHora());
        if(horarioExiste){
            throw new Exception("El horario ya EXISTE en el hora agregada");
        }
        return horarioRepo.save(horario);
    }

    @Override
    public HorarioPelicula obtenerHorario(String codigo) throws Exception
    {
        Optional<HorarioPelicula> horario = horarioRepo.findById(codigo);
        if(horario.isEmpty())
        {
            throw new Exception("No existe el horario con ese codigo");
        }
        return horario.get();
    }

    public boolean HorarioRepetido(LocalTime hora)
    {
        return horarioRepo.findByHora(hora).orElse(null)!= null;

    }

    @Override
    public HorarioPelicula actuaizarHorarios(HorarioPelicula horario) throws Exception
    {

        Optional<HorarioPelicula> horarioGuardado = horarioRepo.findById(horario.getCodigo());
        if(horarioGuardado.isEmpty())
        {
            throw new Exception("El horario NO EXISTE");
        }
        return horarioRepo.save(horario);
    }

    @Override
    public void eliminarHorario(Integer codigo) throws Exception {

    }

    @Override
    public void eliminarHorario(String codigo) throws Exception
    {

        Optional<HorarioPelicula> horarioGuardado = horarioRepo.findById(codigo);
        if(horarioGuardado.isEmpty())
        {
            throw new Exception("El horario NO EXISTE");
        }
        horarioRepo.delete(horarioGuardado.get());
    }

    @Override
    public List<HorarioPelicula> listarHorarios()
    {
        return horarioRepo.findAll();
    }

    //----------------------------------- CRUD DE FUNCIONES -----------------------------------
    @Override
    public Funcion crearFuncion(Funcion funcion) throws Exception
    {

        boolean peliculaEnCartelera = peliculaEnCartelera(funcion.getPelicula().getNombre());

        if(!peliculaEnCartelera)
        {
            throw new Exception("La pelicula no esta en cartelera");
        }
        List<Funcion> funciones = funcionRepo.obtenerFuncionesHorario(funcion.getSala().getCodigo(), funcion.getHorarioPelicula().getDia(), String.valueOf(funcion.getHorarioPelicula().getHora()));

        if(!funciones.isEmpty())
        {
            throw new Exception("Exu+iste una funcion en ese horario");
        }
        return funcionRepo.save(funcion);
    }

    @Override
    public Funcion obtenerFuncion(Integer codigo) throws Exception {
        return null;
    }

    public boolean peliculaEnCartelera(String nombre)
    {
        Pelicula pelicula = peliculaRepo.buscarPeliculaPorNombre(nombre);
        if (pelicula.getEstado()==Estado.CARTELERA)
        {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Funcion obtenerFuncion(String codigo) throws Exception
    {
        Optional<Funcion> funcion = funcionRepo.findById(codigo);
        if(funcion.isEmpty())
        {
            throw new Exception("No existe la funcion con ese codigo");
        }
        return funcion.get();
    }

    @Override
    public Funcion actuaizarFuncion(Funcion funcion) throws Exception
    {
        Optional<Funcion> funcionGuardada = funcionRepo.findById(funcion.getCodigo());
        if(funcionGuardada.isEmpty())
        {
            throw new Exception("La funcion NO EXISTE");
        }
        return funcionRepo.save(funcion);
    }

    @Override
    public void eliminarFuncion(Integer codigo) throws Exception {

    }

    @Override
    public void eliminarFuncion(String codigo) throws Exception
    {
        Optional<Funcion> funcionGuardada = funcionRepo.findById(codigo);
        if(funcionGuardada.isEmpty()){
            throw new Exception("La funcion NO EXISTE");
        }
        funcionRepo.delete(funcionGuardada.get());
    }

    @Override
    public List<Funcion> listarFuncion()
    {
        return funcionRepo.findAll();
    }

    //------------------------------- CRUD DE SALAS --------------------------------------
    @Override
    public Sala crearSalas(Sala sala)
    {
        return salaRepo.save(sala);
    }

    @Override
    public Sala obtenerSala(Integer codigo) throws Exception {
        return null;
    }

    @Override
    public Sala obtenerSala(String codigo) throws Exception
    {
        Optional<Sala> sala = salaRepo.findById(codigo);
        if(sala.isEmpty())
        {
            throw new Exception("No existe la sala con ese codigo");
        }
        return sala.get();
    }

    @Override
    public Sala actualizarSala(Sala sala) throws Exception
    {
        Optional<Sala> salaGuardada = salaRepo.findById(sala.getCodigo());
        if(salaGuardada.isEmpty())
        {
            throw new Exception("La sala NO EXISTE");
        }
        return salaRepo.save(sala);
    }

    @Override
    public void eliminarSala(Integer codigoSala) throws Exception {

    }

    @Override
    public void eliminarSala(String codigoSala) throws Exception {
        Optional<Sala> salaGuardada = salaRepo.findById(codigoSala);
        if(salaGuardada.isEmpty())
        {
            throw new Exception("La sala NO EXISTE");
        }
        salaRepo.delete(salaGuardada.get());
    }

    @Override
    public List<Sala> listarSala()
    {
        return salaRepo.findAll();
    }

}
