package org.example;

import java.util.Date;

public abstract class MLTransaccion {

    private double monto;
    private Date fecha;

    public MLTransaccion(double monto, Date fecha) {
        this.monto = monto;
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "monto=" + monto +
                ", fecha=" + fecha +
                '}';
    }
}