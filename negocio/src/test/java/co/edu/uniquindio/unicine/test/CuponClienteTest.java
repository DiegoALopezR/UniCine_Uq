package co.edu.uniquindio.unicine.test;

import co.edu.uniquindio.unicine.entidades.CuponCliente;
import co.edu.uniquindio.unicine.repo.CuponClienteRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

public class CuponClienteTest
{
    @Autowired
    private CuponClienteRepo cuponClienteRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCuponesTodosClientes(){
        List<Object[]> cupones = cuponClienteRepo.obtenerCuponesTodosClientes();
        cupones.forEach( o ->
                System.out.println(o [0] + "," + o[1] + "," + o[2])
        );
    }


    @Test
    @Sql("classpath:dataset.sql")
    public void redimirCupon() throws Exception{
        CuponCliente cuponGuardado = cuponClienteRepo.buscarCuponClientePorCodigoCupon(1);
        if(cuponGuardado == null) {
            throw new Exception("El cupon no existe");
        }
        cuponGuardado.setEstado(" ");
        cuponClienteRepo.save(cuponGuardado);

        cuponGuardado = cuponClienteRepo.buscarCuponClientePorCodigoCupon(1);
        System.out.println(cuponGuardado);
    }

}
