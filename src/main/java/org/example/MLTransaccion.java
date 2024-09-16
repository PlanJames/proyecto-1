package org.example;

import java.util.Date;

public abstract class MLTransaccion {

    private double monto;
    private Date fecha;
    private TipoDeCuenta tipoDeCuenta;

    public MLTransaccion(double monto, Date fecha, TipoDeCuenta tipoDeCuenta) {
        this.monto = monto;
        this.fecha = fecha;
        this.tipoDeCuenta = this.tipoDeCuenta;
    }

    public double getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }
    public TipoDeCuenta getTipoDeCuenta() {
        return tipoDeCuenta; // Método para obtener el tipo de cuenta
    }
    @Override
    public String toString() {
        return "Transaccion{" +
                "monto=" + monto +
                ", fecha=" + fecha +
                ", tipoDeCuenta=" + tipoDeCuenta +
                '}';
    }
}