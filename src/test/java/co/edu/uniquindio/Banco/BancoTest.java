package co.edu.uniquindio.Banco;
import static org.junit.jupiter.api.Assertions.*;

import co.edu.uniquindio.Banco.enums.Categoria;
import co.edu.uniquindio.Banco.model.*;
import org.junit.jupiter.api.Test;

public class BancoTest {
    Banco banco = new Banco("1234", "Banco UQ");

    @Test
    public void crearUsuarioTest(){
        boolean usuario = banco.crearUsuario("Estefania", "La tebaida", "12345", "Estefmd@gmail.com", "12345");
        assertTrue(usuario);
    }

    @Test
    public void actualizarUsuarioTest(){
       banco.crearUsuario("Estefania", "La tebaida", "12345", "Estefmd@gmail.com", "12345");
       banco.actualizarDatosUsuario("12345", "Armenia", "estef@gmail.com");

       String esperadoD = "Armenia";
       String realD = banco.buscarUsuario("12345").getDireccion();
       assertEquals(esperadoD, realD);

        String esperadoC = "estef@gmail.com";
        String realC = banco.buscarUsuario("12345").getCorreo();
        assertEquals(esperadoC, realC);
    }

    @Test
    public void eliminarUsuarioTest(){
        banco.crearUsuario("Valen", "Armeia", "123456", "valen@gmail.com", "123456");
        Usuario usuario = banco.buscarUsuario("123456");
        assertNotNull(usuario);

        banco.eliminarUsuario("123456");
        Usuario usuarioEliminado = banco.buscarUsuario("123456");
        assertNull(usuarioEliminado);
    }

}
