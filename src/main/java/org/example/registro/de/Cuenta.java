package org.example.registro.de;

public class Cuenta {
    private String cuentaId;
    private double saldo;

    public Cuenta(String cuentaId, double saldoInicial) {
        this.cuentaId = cuentaId;
        this. saldo = saldoInicial;
    }
    public String getCuentaId() {
        return cuentaId;
    }
    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        this.saldo += monto;
    }

    public boolean retirar(double monto){
        if (monto > saldo){
            return false;
        }
        this.saldo -= monto;
        return true;
    }
}
