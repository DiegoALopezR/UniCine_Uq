package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.dto.PeliculaFuncion;
import co.edu.uniquindio.unicine.entidades.*;
import co.edu.uniquindio.unicine.repo.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ClienteServicioImp
{
    @Autowired
    private ClienteRepo clienteRepo;
    private PeliculaRepo peliculaRepo;
    private EmailServicio emailServicio;
    private Foro foroRepo;
    private Puntuacion puntuacionRepo;
    private FuncionRepo funcionRepo;
    private CuponRepo cuponRepo;
    private CuponClienteRepo cuponClienteRepo;
    private ConfiteriaRepo confiteriaRepo;
    private CompraRepo compraRepo;
    private EntradaRepo entradaRepo;

    private CompraConfiteriaRepo compraConfiteriaRepo;
    private Float ValorTotalConDescuento;

    //Construtor
    public void ClienteServicioImpl(ClienteRepo clienteRepo, PeliculaRepo peliculaRepo, EmailServicio emailServicio, Foro foroRepo, Puntuacion puntuacionRepo, FuncionRepo funcionRepo, CuponRepo cuponRepo, CuponClienteRepo cuponClienteRepo, ConfiteriaRepo confiteriaRepo, CompraRepo compraRepo, EntradaRepo entradaRepo, CompraConfiteriaRepo compraConfiteriaRepo)
    {
        this.clienteRepo = clienteRepo;
        this.peliculaRepo = peliculaRepo;
        this.emailServicio = emailServicio;
        this.foroRepo = foroRepo;
        this.puntuacionRepo = puntuacionRepo;
        this.funcionRepo = funcionRepo;
        this.cuponRepo = cuponRepo;
        this.cuponClienteRepo = cuponClienteRepo;
        this.confiteriaRepo = confiteriaRepo;
        this.compraRepo = compraRepo;
        this.entradaRepo = entradaRepo;
        this.compraConfiteriaRepo = compraConfiteriaRepo;
    }

    //------------------------------------LOGIN----------------------------------------
    /**
     * @Param String correo, String password
     * @Return administrador
     * Este metodo se registra un cliente, el cual primero se validad su autenticacion
     */
    @Override
    public Cliente login(String correo, String password) throws Exception
    {
        Cliente cliente = clienteRepo.comprobarAutenticacion(correo, password);

        if(cliente == null)
        {
            throw new Exception("Los Datos de Autentificacion son INCORRECTOS");
        }

        //validar  estado del cliente
        cliente = clienteRepo.obtenerPorEstado(cliente.getCedula(), true);
        if(cliente == null)
        {
            throw new Exception("No se activado la cuenta para ingresar");
        }

        return cliente;
    }

    //----------------------------------- BUSCAR PELICULA -------------------------------
    @Override
    public List<Pelicula> buscarPeliculaPorNombre(String nombre) throws Exception
    {
        List<Pelicula> peliculaGuardada = peliculaRepo.findByNombre(nombre);

        if(peliculaGuardada.isEmpty())
        {
            throw new Exception("La pelicula NO EXISTE");
        }

        return peliculaGuardada;
    }

    @Override
    public List<Pelicula> buscarPeliculaPorEstadoCiudad(Integer codigo, Estado estadoPelicula) throws Exception
    {
        List<Pelicula> peliculaGuardada = funcionRepo.listarPeliculaEstadoCiudad(codigo, estadoPelicula);
        return peliculaGuardada;
    }

    @Override
    public List<Pelicula> buscarPeliculaPorEstado(Estado estadoPelicula) throws Exception
    {
        List<Pelicula> peliculaGuardada = funcionRepo.listarPeliculaEstado(estadoPelicula);
        if(peliculaGuardada.isEmpty())
        {
            throw new Exception("La pelicula NO EXISTE");
        }
        return peliculaGuardada;
    }
    //---------------------------------- CRUD DE CLIENTE --------------------------------
    @Override
    public Cliente registrarCliente(Cliente cliente) throws Exception
    {

        boolean correoExiste = esRepetido(cliente.getEmail());
        if(correoExiste)
        {
            throw new Exception("El Correo ya esta en Uso");
        }
        //cedula
        boolean cedulaExiste = cedulaRepetida(String.valueOf(cliente.getCedula()));
        if(cedulaExiste)
        {
            throw  new Exception("La cedula ingresada ya existe");
        }

        Cliente clienteRegistrado =  clienteRepo.save(cliente);

        CuponCliente cupon = new CuponCliente();
        cupon.setClienteCupon(clienteRegistrado);
        cupon.setCupon( cuponRepo.findById(" ").get() );
        cupon.setEstado(" ");
        cuponClienteRepo.save(cupon);

        //Generar un codigo para el cupon

        emailServicio.enviarEmail("Registro de cuenta en UniCine", "Hola "+cliente.getNombreCompleto()+" es un gusto que haya registrado en Unicine, para activar su cuenta ingrese en el siguiente link: url", cliente.getEmail());
        emailServicio.enviarEmail("Regalo Cupon Por Registro", "Hola "+cliente.getNombreCompleto()+" Haz adquirido un cupon por registrarte, el codigo es: "+cupon.getCodigo(), cliente.getEmail());

        return clienteRegistrado;
    }

    private boolean esRepetido(String correo){
        return clienteRepo.findByEmail(correo).orElse(null) != null;
    }

    private boolean cedulaRepetida(String cedula)
    {
        return clienteRepo.existsById(cedula);
    }
    @Override
    public Cliente obtenerClientePorCedula(String cedula) throws Exception
    {

        Optional<Cliente> clienteGuardado = clienteRepo.findById(cedula);

        if(clienteGuardado.isEmpty())
        {
            throw new Exception("El cliente NO EXISTE");
        }

        return clienteGuardado.get();
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) throws Exception
    {

        Optional<Cliente> clienteGuardado = clienteRepo.findById(String.valueOf(cliente.getCedula()));

        if (clienteGuardado.isEmpty())
        {
            throw new Exception("El cliente NO EXISTE");
        }
        return clienteRepo.save(cliente);
    }

    @Override
    public void eliminarCliente(String codigoCliente) throws Exception
    {

        Optional<Cliente> clienteGuardado = clienteRepo.findById(codigoCliente);

        if(clienteGuardado.isEmpty())
        {
            throw new Exception("El cliente NO EXISTE");
        }

        clienteRepo.delete(clienteGuardado.get());
    }

    @Override
    public List<Cliente> listarClientes()
    {
        return clienteRepo.findAll();
    }

    //------------------------------ LISTAR LA COMPRAS -------------------------------
    @Override
    public void listarHitorialCompra(String cedulaCliente) throws Exception
    {
        Boolean cedulaExiste = clienteRepo.existsById(cedulaCliente);
        if(cedulaExiste == false)
        {
            throw new Exception("La cedula del cliente ingresado no hiciste");
        }
        List<Compra> compras = clienteRepo.obtenerComprasCliente(Integer.valueOf(cedulaCliente));
        compras.forEach(System.out::println);
    }

    //---------------------------------- HACER UNA COMPRA --------------------------------
    @Override
    public Compra hacerCompra(Cliente cliente, List<Entrada> entradas, List<CompraConfiteria> compraConfiteria, MedioPago medioPago, Cupon cupon, Funcion funcion) throws Exception
    { // Cliente, Entradas, Canfiterias, medio de pago, cupon, funcion

        Compra compra = new Compra();

        //verificar cliente,
        Optional<Cliente> clienteExiste = clienteRepo.findById(String.valueOf(cliente.getCedula()));
        if (clienteExiste.isEmpty()){
            throw new Exception("Cliente no existe");
        }

        //verificar que las sillas esten disponibles

        for (Entrada entrada : entradas )
        {
            Entrada entradaOcupada = funcionRepo.verificarSilla(funcion.getCodigo(), entrada.getFila(), entrada.getColumna());
            if (entradaOcupada == null)
            {
                throw new Exception("Las entredas seleccionadas ya estan ocupadas");
            }
        }
        //redimir el cupon si no es null

        if(cupon != null)
        {

            Optional<CuponCliente> cuponExiste = cuponClienteRepo.findById(cupon.getCodigo());
            if (cuponExiste.isEmpty())
            {
                throw new Exception("El cupon no existe");
            }

        }

        boolean cuponCliente = false;

        List<CuponCliente> cuponesCliente = clienteRepo.obtenerCuponesPorCedula(cliente.getCedula());
        for (CuponCliente cup : cuponesCliente)
        {
            if (cup.getCupon().getCodigo() == cupon.getCodigo())
            {
                cuponCliente = true;
            }
        }
        if (!cuponCliente)
        {
            throw new Exception("El cupon no es del cliente");
        }
        redirCupon(Integer.valueOf(cupon.getCodigo()));

        //sumar los precios, aplicar el descuento

        Double valorTotal = calcularValorTotal(entradas, compraConfiteria);
        Double valorTotalConDescuento = calcularValorTotalConDescuento (valorTotal, cupon.getDescuento());

        //persiste la compra
        compra.setPrecioTotal(ValorTotalConDescuento);
        compra.setFechaCompra(LocalDateTime.now());
        compra.setMedioPago(medioPago);
        compra.setEntradas(entradas);
        compraRepo.save(compra);

        //Mandar compra a todas las entrdas y las conprasConfiterias que lleagn
        for (Entrada entrada : entradas)
        {
            entrada.setCompra(compra);
            entradaRepo.save(entrada);
        }

        for (CompraConfiteria compConfi : compraConfiteria)
        {
            compConfi.setCompraConfi(compra);
            compraConfiteriaRepo.save(compConfi);
        }

        //Mandar cupon primera compra
        List<Compra> compras = clienteRepo.obtenerComprasCliente(cliente.getCedula());

        if (compras.size() == 1)
        {
          emailServicio.enviarEmail("Primera compra", "Hola " + cliente.getNombreCompleto() + " Por tu primera compra, haz adquirido un cupon " , cliente.getEmail());
        }

        return compra;
    }

    public Double calcularValorTotal(List<Entrada> entradas,List<CompraConfiteria> compraConfiteria ){

        Double valorTotal = 0.0;

        for (CompraConfiteria compConfi : compraConfiteria){
            valorTotal = valorTotal + (compConfi.getPrecio()) * (compConfi.getUnidades());
        }
        for (Entrada entrada : entradas){
            valorTotal = valorTotal + entrada.getCompra().getPrecioTotal();
        }
        System.out.println(valorTotal) ;
        return valorTotal ;
    }

    public Double calcularValorTotalConDescuento(Double valorTotal, Double descuento )
    {

        Double valorConDescuento = valorTotal-(valorTotal*descuento);
        return valorConDescuento ;
    }

    //------------------------------------ REDMIR CUPON -----------------------------------
    @Override
    public boolean redirCupon(Integer codigoCupon) throws Exception
    {
        CuponCliente cuponGuardado = cuponClienteRepo.buscarCuponClientePorCodigoCupon(codigoCupon);
        if(cuponGuardado == null)
        {
            throw new Exception("El cupon no existe");
        }
        cuponGuardado.setEstado(" ");
        cuponClienteRepo.save(cuponGuardado);
        return true;
    }

    //------------------------------------- Cambiar Contraseña ------------------------------
    public void enviarLinkRecuperacion(String correo)
    {
        emailServicio.enviarEmail("Recuperacion password", "Para recupear la contraseña ingrese a: []", correo);
    }

    @Override
    //no usar ñs
    public boolean cambiarContraseña(String correo, String passwordNueva ) throws Exception
    {

        Cliente cliente = clienteRepo.findByEmail(correo).orElse(null);
        enviarLinkRecuperacion(correo);

        if(cliente==null)
        {
            throw new Exception("El cliente no se encontro con el correo ingresado");
        }

        cliente.setContrasena(passwordNueva);
        clienteRepo.save(cliente);

        return true;
    }


    /*------------------------------- Metodos de filtrar --------------------------------------------------*/
   // @Override
   // public List<PeliculaFuncion> listarPeliculasFunciones(String nombre)
    //{
      //  return peliculaRepo.buscarPelicula("nombre");
    //}


}
