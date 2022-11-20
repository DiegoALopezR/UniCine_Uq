package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.CuponCliente;
import co.edu.uniquindio.unicine.entidades.Puntuacion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, String>
{
    //@Query("select c from Cliente c where c.email = ?1")

   // Cliente obtener(String email);

    //@Query("select c from Cliente c where c.email = :email and c.contrasena = :contrasena")
   // Cliente comprobarAutentificacion (String email , String contrasena);

    //@Query("select c from Cliente c where c.estado = :estado")
    //List<Cliente> obtenerPorEstado (boolean estado, Pageable paginador);

    // Esta consulta busca un cliente dado el correo del cliente
    @Query("Select c from Cliente c where c.email = :correo")
    Cliente buscarCliente(String correo);

    // Esta consulta busca un cliente dado la cedula  del cliente
    @Query("Select c from Cliente c where c.email = :cedula")
    Cliente buscarCliente(Integer cedula);

    // Esta consulta busca un cliente dado el correo del cliente
    Optional<Cliente> findByEmail(String correo);

    //Esta consulta valida la autenticacion del cliente en el login al momento de ingresar
    @Query("select c from Cliente c where c.email = :correo and c.contrasena = :contrasena")
    Cliente comprobarAutenticacion (String correo, String contrasena);

    // Esta consulta busca un cliente dado el correo y la constraseña del cliente
    Cliente findByEmailAndContrasena(String email, String clave);

    //Esta consulta obtiene el estado de la cuenta de un cliente, dado su estado y separado por un paginador
    @Query("select c from Cliente c where c.estado = :estado")
    List<Cliente> obtenerPorEstados (Boolean estado, Pageable paginador);

    //Esta consulta obtiene el estado de la cuenta de un cliente, dado su estado
    @Query("select c from Cliente c where c.cedula = :cedula and c.estado = :estado")
    Cliente obtenerPorEstado(Integer cedula, Boolean estado);

    //Esta consulta obtiene las compras de un cliente dado su correo (1)
    @Query("select comp from Cliente cliente, in(cliente.compras) comp where cliente.email = :correo")
    List<Compra> obtenerCompra (String correo);

    //Esta consulta obtiene las compras de un cliente dado su cedula (2)
    @Query("select c from Compra c where c.cliente.cedula = :cedula")
    List<Compra> obtenerComprasCliente (Integer cedula);

    //Esta consulta obtiene las compras de un cliente dado su correo(3)
    @Query("select comp from Cliente cliente join cliente.compras comp where cliente.email = :correo")
    List<Compra> obtenerCompraOpcion3(String email);

    //Esta consulta obtiene los cupones de un cliente dado su correo
    @Query("select cup from Cliente cliente join cliente.cuponClientes cup where cliente.email = :correo")
    List<CuponCliente> obtenerCupones(String email);

    @Query("select cup from Cliente cliente join cliente.cuponClientes cup where cliente.cedula = :cedula")
    List<CuponCliente> obtenerCuponesPorCedula(Integer cedula);

    //Esta consulta obtiene la compra de todos los usuarios, los distribuye por el nomnre y la cedula
    @Query("select cliente.nombreCompleto, cliente.email, comp from Cliente cliente left join cliente.compras comp")
    List<Object[]> obtenerCompraTodos();


       // /Dada la cedula de un cliente, retornar todas las calificaciones que ha generado/
    @Query("select c from Cliente cliente join cliente.puntuaciones c where cliente.cedula = :cedula")
    List<Puntuacion> obtenerCalificaciones(Integer cedula);

    // Esta consulta cuenta todas las compras de un cliete dado su cedula
    @Query("select count(cliente.compras) from Cliente cliente where cliente.cedula = :cedula")
    Integer obtenerCantidadComprasCliente(Integer cedula);





}
