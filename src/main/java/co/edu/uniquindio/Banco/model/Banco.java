package co.edu.uniquindio.Banco.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nit;
    private String nombre;
    private ArrayList<Usuario> listaUsuarios;
    private ArrayList<Cuenta> listaCuentas;

    public Banco() {
    }

    public Banco(String nit, String nombre, ArrayList<Usuario> listaUsuarios, ArrayList<Cuenta> listaCuentas) {
        this.nit = nit;
        this.nombre = nombre;
        this.listaUsuarios = listaUsuarios;
        this.listaCuentas = listaCuentas;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ArrayList<Cuenta> getListaCuentas() {
        return listaCuentas;
    }

    public void setListaCuentas(ArrayList<Cuenta> listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public String crearUsuario(String nombre, String direccion, String id, String correo, String contrasena){
        if (buscarUsuario(id)==null){
            Usuario usuario = new Usuario(nombre, direccion,id,correo,contrasena);
            listaUsuarios.add(usuario);
            return "Usuario crado con exito";
        }else{
            return "El id ya se encuentra en el sistema, intentelo nuevamente";
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

    public String eliminarUsuario(String id){
        Usuario usuario = buscarUsuario(id);
        if (usuario!=null){
            eliminarCuentasUsuario(id);
            listaUsuarios.remove(usuario);
            return "Usuario eliminado";
        }else{
            return "Id no encontrado";
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

    public String eliminarCuenta(String idCuenta){
        Cuenta cuenta = buscarCuenta(idCuenta);
        if(cuenta!=null){
            listaCuentas.remove(cuenta);
            return  "Cuenta eliminada con exito";
        }else {
            return  "Ocurrio un error al eliminar la cuenta";
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

    public boolean validarExistencia(String numeroCuenta){
        int tamanioLista = getListaCuentas().size();
        boolean exist = false;

        for (int i = 0; i < tamanioLista; i++) {
            Cuenta cuenta = getListaCuentas().get(i);
            if (cuenta.getIdCuenta().equalsIgnoreCase(numeroCuenta)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public boolean validarSaldo(Cuenta cuenta, double valor){
        if (cuenta.getSaldo()>=valor){
            return true;
        }else {
            return false;
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
