package co.edu.uniquindio.Banco.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Banco {
    private String nit;
    private String nombre;
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private ArrayList<Cuenta> listaCuentas = new ArrayList<>();

    public Banco() {
    }

    public Banco(String nit, String nombre) {
        this.nit = nit;
        this.nombre = nombre;
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

    public boolean crearUsuario(String nombre, String direccion, String id, String correo, String contrasena){
        if (buscarUsuario(id)==null){
            Usuario usuario = new Usuario(nombre, direccion,id,correo,contrasena);
            listaUsuarios.add(usuario);
            return true;
        }else{
            return false;
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

    public boolean actualizarDatosUsuario(String id, String nuevaDireccion, String nuevoCorreo) {
        Usuario usuario = buscarUsuario(id);
        if (usuario != null) {
            usuario.setDireccion(nuevaDireccion);
            usuario.setCorreo(nuevoCorreo);
            return true;
        } else {
            return false;
        }
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

    public String consultarSaldoCuenta(String idUsuario, String contrasena, String idCuenta){
        Usuario usuario = buscarUsuario(idUsuario);
        String mensaje = "";
        if (usuario!= null && usuario.getContrasena().equals(contrasena)){
            Cuenta cuenta = buscarCuenta(idCuenta);
            mensaje = "Saldo"+cuenta.getSaldo()+"\n"+
                    mostrarTransacciones(idCuenta);
        }
        return mensaje;
    }

    public String generarNumeroCuenta() {
        Random random = new Random();
        String numeroAleatorio = "";
        for (int i = 0; i < 10; i++) {
            numeroAleatorio += random.nextInt(10);
        }
        return numeroAleatorio;
    }

    public String crearCuenta(double saldo, Usuario propietario){
        boolean existe = true;
        String numeroCuenta = "";
        do {
            numeroCuenta = generarNumeroCuenta();
            if (validarExistenciaCuenta(numeroCuenta)== false){
                existe = false;
            }
        }while(existe == true);

        Cuenta cuenta = new Cuenta(numeroCuenta, saldo, propietario);
        return numeroCuenta;
    }

    public boolean validarExistenciaCuenta(String numeroCuenta){
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

    public boolean validarSaldoCuenta(Cuenta cuenta, double valor){
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
