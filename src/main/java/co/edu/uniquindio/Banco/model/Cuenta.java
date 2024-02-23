package co.edu.uniquindio.Banco.model;

import co.edu.uniquindio.Banco.enums.Categoria;

import java.time.LocalDate;
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

    /**
     * Metodo para cobrar una transaccion
     * @param cuentaRemitente
     * @param cuentaDestinatario
     * @param valor
     */
    public void cobrarTransaccion(Cuenta cuentaRemitente, Cuenta cuentaDestinatario, double valor) {
        double saldoRemitente = cuentaRemitente.getSaldo();
        saldoRemitente -= valor;
        cuentaRemitente.setSaldo(saldoRemitente);
        double saldoDestinatario = cuentaDestinatario.getSaldo();
        saldoDestinatario += valor;
        cuentaDestinatario.setSaldo(saldoDestinatario);
    }

    /**
     * Metodo para crear una transaccion
     * @param banco
     * @param valor
     * @param categoria
     * @param remitente
     * @param destinatario
     * @return String
     */
    public String crearTransaccion(Banco banco, double valor, Categoria categoria, String remitente, String destinatario) {
        if (banco.validarExistencia(remitente)){
            Cuenta cuentaRemitente = banco.buscarCuenta(remitente);
            boolean existeciaDestinatario = banco.validarExistencia(destinatario);
            valor += 200;
            boolean disponibilidadSaldo = banco.validarSaldo(cuentaRemitente, valor+200);
            if (existeciaDestinatario && disponibilidadSaldo){
                Cuenta cuentaDestinatario = banco.buscarCuenta(destinatario);
                cobrarTransaccion(cuentaRemitente, cuentaDestinatario, valor);
                Transaccion transaccion = new Transaccion(valor-200, LocalDate.now(), categoria, cuentaRemitente, cuentaDestinatario);
                getLitsaTransacciones().add(transaccion);
                return "Transaccion existosa";
            }else {
                return "No es posible hacer la transaccion";
            }
        }else {
            return "No es posible hacer la transaccion";
        }



    }
}
