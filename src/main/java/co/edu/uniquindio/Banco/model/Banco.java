package co.edu.uniquindio.Banco.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nit;
    private String nombre;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Cuenta> listaCuentas;

    public void crearUsuario(String nombre, String direccion, String id, String correo, String contrasena){
        if (buscarUsuario(id)==null){
            Usuario usuario = new Usuario(nombre, direccion,id,correo,contrasena);
            listaUsuarios.add(usuario);
            JOptionPane.showMessageDialog(null, "Usuario crado con exito");
        }else{
            JOptionPane.showMessageDialog(null, "El id ya se encuentra en el sistema, intentelo nuevamente");
        }
    }

    public Usuario buscarUsuario(String id){
        for(Usuario usuario: listaUsuarios){
            if (usuario.getId().equals(id)){
                return usuario;
            }
        }
        return null;
    }

    public void actualizarUsuario(String nombre, String direccion, String id, String correo, String contrasena){

    }

    public void eliminarUsuario(String id){
        Usuario usuario = buscarUsuario(id);
        if (usuario!=null){
            eliminarCuentasUsuario(id);
            listaUsuarios.remove(usuario);
            JOptionPane.showMessageDialog(null, "Usuario eliminado");
        }else{
            JOptionPane.showMessageDialog(null, "Id no encontrado");
        }
    }

    public void eliminarCuentasUsuario(String idUsuario) {
        List<Cuenta> cuentasAEliminar = new ArrayList<>();
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getPropietario().getId().equals(idUsuario)) {
                cuentasAEliminar.add(cuenta);
            }
        }
        listaCuentas.removeAll(cuentasAEliminar);
    }

    public Cuenta buscarCuenta(String idCuenta){
        for (Cuenta cuenta: listaCuentas){
            if(cuenta.getIdCuenta().equals(idCuenta)){
                return cuenta;
            }
        }
        return null;
    }

    public void eliminarCuenta(String idCuenta){
        Cuenta cuenta = buscarCuenta(idCuenta);
        if(cuenta!=null){
            listaCuentas.remove(cuenta);
            JOptionPane.showMessageDialog(null, "Cuenta eliminada con exito");
        }
    }

    public void consultarSaldoCuenta(String idUsuario, String contrasena, String idCuenta){
        Usuario usuario = buscarUsuario(idUsuario);
        if (usuario!= null && usuario.getContrasena().equals(contrasena)){
            Cuenta cuenta = buscarCuenta(idCuenta);
            String mensaje = "Saldo"+cuenta.getSaldo()+"\n"+
                    mostrarTransacciones(idCuenta);

        }
    }

    public String mostrarTransacciones(String idCuenta){
        Cuenta cuenta = buscarCuenta(idCuenta);
        if (cuenta != null){
            String mensaje = "";
            for (Transaccion transaccion: cuenta.getLitsaTransacciones()){
                mensaje+= "-------------- \n"+
                        "Remitente: " + transaccion.getRemitente()+"\n"+
                        "Destinatario: " + transaccion.getDestinatario()+"\n"+
                        "Valor: " + transaccion.getValor()+"\n"+
                        "Categoría: " + transaccion.getCategoria()+"\n"+
                        "Fecha: " + transaccion.getFecha()+"\n"+
                        "----------------- \n";
            }
            return mensaje;
        }
        return  null;
    }


}
