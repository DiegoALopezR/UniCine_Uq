package co.edu.uniquindio.unicine.repo;

import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, String>
{

    //@Query("Select c From Compra c where c.entradas = :entradas")
    //Compra obtener(String entrada);

    //Compra findByEntradas(List<Entrada> entradas);

    //@Query("select c from Compra c where c.entradas = :entradas and c.codigo =:codigo ")
    //Compra comprobarAutenticacion(String entradas, String codigo);

    //Compra findByEntradasAndCodigo(List<Entrada> entradas, Integer codigo);


    //Esta consulta obtiene las entradas de una compra dado el codigo de una compra
    @Query("select e from Compra compra join compra.entradas e where compra.codigo = :codigo")
    List<Entrada> obtenerEntradas(Integer codigo);

    //Esta consulta cuenta todos los cupones que tiene un cliente, muestra la cedula del cliente y el total de los cupones
    @Query("select compra.cliente.cedula, count(compra) from Compra compra where compra.clienteCupon is not null group by compra.cliente")
    List<Object[]> contarCuponesRedimidos();

    // Esta consulta suma el valor total de las compras de un cliente, dada la cedula del cliente
    @Query("select sum(c.precioTotal) from Compra c where c.cliente.cedula = :codigoCliente")
    Double calcularValorTotalGastado(Integer codigoCliente);

    //Esta consulta obtiene la compra mas costosa de un cliente
    @Query("select c1.cliente, c1 from Compra c1 where c1.precioTotal = (select max(c.precioTotal) from Compra c)")
    List<Object[]> obtenerCompraMaxCostosa();

    //Esta consulta se toma la informacionDTO para dar los atributos, la cual obtiene un usuario dado la cedula del cliente
    //@Query("select new co.edu.uniquindio.unicine.dto.InformacionCompraDTO( c.precioTotal, c.fechaCompra, c.funcion, (select sum(e.precio) from Entrada e where e.compra.codigo = c.codigo), (select sum(cc.precio*cc.unidades) from CompraConfiteria cc where cc.compra.codigo = c.codigo)) from Compra c where c.cliente.cedula = :codigoCliente")
    //List<InformacionCompraDTO> obtenerInformacionUsuario(Integer codigoCliente);

    //Esta consulta obtiene la pelicula mas vista dado el codigo de una ciudad
    @Query("select c.funcion.pelicula, count(c) from Compra c where c.funcion.sala.teatro.ciudad.codigo = :codigoCiudad group by c.funcion.pelicula")
    List<Object[]> obtenerPeliculaMasVista(Integer codigoCiudad);



}
