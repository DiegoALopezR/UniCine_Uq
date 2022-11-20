package co.edu.uniquindio.unicine.test;


import co.edu.uniquindio.unicine.entidades.Administrador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

public class AdministradorTest
{
    @Test
    @Sql("classpath:dataset.sql")
    public void loginTest() throws Exception {

        try {
            Administrador administrador = administradorServicio.login("lopezjose@gmail.com","9876");
            Assertions.assertEquals("lopezjose@gmail.com" , administrador.getCorreo());
        }catch (Exception e){
            throw new Exception(e);
        }
    }



}
