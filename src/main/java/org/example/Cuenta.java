package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class Cuenta {
    private String numeroCuenta;
    private double saldo;

    // Constructor
    public Cuenta(String numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    // Obtener el número de cuenta
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    // Obtener el saldo actual de la cuenta
    public double getSaldo() {
        return saldo;
    }

    // Método para depositar dinero en la cuenta
    public void depositar(double cantidad) {
        saldo += cantidad;
    }

    // Método para retirar dinero de la cuenta
    public void retirar(double cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    // Mostrar información de la cuenta
    public void mostrarInformacion() {
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Saldo: " + saldo);
    }
}

