package co.edu.uniquindio.Banco.model;

import java.util.ArrayList;

public class Cuenta {
    private String idCuenta;
    private double saldo;
    private Usuario propietario;
    private Banco banco;
    private ArrayList<Transaccion> litsaTransacciones;

    public Cuenta(String idCuenta, double saldo, Usuario propietario) {
        this.idCuenta = idCuenta;
        this.saldo = saldo;
        this.propietario = propietario;
    }

    public Cuenta() {
    }

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public Banco getBanco() { return banco; }

    public void setBanco(Banco banco) { this.banco = banco; }

    public ArrayList<Transaccion> getLitsaTransacciones() { return litsaTransacciones; }

    public void setLitsaTransacciones(ArrayList<Transaccion> litsaTransacciones) { this.litsaTransacciones = litsaTransacciones; }
}
