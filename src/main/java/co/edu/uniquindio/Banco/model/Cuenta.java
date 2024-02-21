package co.edu.uniquindio.Banco.model;

public class Cuenta {
    public String idCuenta;
    private double saldo;
    private Usuario propietario;

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
}
