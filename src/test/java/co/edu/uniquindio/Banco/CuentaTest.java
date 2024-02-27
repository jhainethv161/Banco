package co.edu.uniquindio.Banco;
import static org.junit.jupiter.api.Assertions.*;

import co.edu.uniquindio.Banco.enums.Categoria;
import co.edu.uniquindio.Banco.model.*;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;

public class CuentaTest {
    Banco banco = new Banco("1234", "Banco UQ");
    @Test
    public void crearTransaccionTest(){
        boolean usuario1Creado = banco.crearUsuario("Estfania", "Mz 10 # 85", "1094331881", "estefania@gmail.com", "tefi05");
        boolean usuario2Creado = banco.crearUsuario("Valentina", "Mz 5 # 23", "1094521211", "valen@gmail.com", "vale05");
        if (usuario1Creado && usuario2Creado){
            Usuario usuario1 = banco.buscarUsuario("1094331881");
            Usuario usuario2 = banco.buscarUsuario("1094521211");
            String numeroCuenta1 = banco.crearCuenta(200000, usuario1);
            String numeroCuenta2 = banco.crearCuenta(200000, usuario2);
            Cuenta cuenta1 = banco.buscarCuenta(numeroCuenta1);
            Cuenta cuenta2 = banco.buscarCuenta(numeroCuenta2);
            String respuesta = cuenta1.crearTransaccion(banco, 50000, Categoria.COMIDA, cuenta1.getIdCuenta(), numeroCuenta2);
            assertEquals("Transaccion existosa", respuesta);
            assertEquals(149800, cuenta1.getSaldo());
            assertEquals(250000, cuenta2.getSaldo());
        }
    }
}
