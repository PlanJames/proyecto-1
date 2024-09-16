package org.example;

import org.example.MLTransaccion;

import java.util.Date;

public class Deposito extends MLTransaccion {

    // Constructor
    public Deposito(double monto, Date fecha) {
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Deposito{" +
                "monto=" + Deposito.this.getClass() +
                ", fecha=" + getClass() +
                '}';
    }
}


