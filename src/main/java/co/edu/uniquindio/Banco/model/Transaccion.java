package co.edu.uniquindio.Banco.model;

import co.edu.uniquindio.Banco.enums.Categoria;

import java.time.LocalDate;

public class Transaccion {
    private double valor;
    private LocalDate fecha;
    private Categoria categoria;
    private Cuenta remitente;
    private Cuenta destinatario;


    public Transaccion() {
    }

    public Transaccion(double valor, LocalDate fecha, Categoria categoria, Cuenta remitente, Cuenta destinatario) {
        this.valor = valor;
        this.fecha = fecha;
        this.categoria = categoria;
        this.remitente = remitente;
        this.destinatario = destinatario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Cuenta getRemitente() {
        return remitente;
    }

    public void setRemitente(Cuenta remitente) {
        this.remitente = remitente;
    }

    public Cuenta getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Cuenta destinatario) {
        this.destinatario = destinatario;
    }
}
