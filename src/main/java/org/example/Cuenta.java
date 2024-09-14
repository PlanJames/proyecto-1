package org.example;

import java.io.IOException;
import java.util.UUID;

public class Cuenta {
    private TipoDeCuenta tipoDeCuenta;
    private UUID numeroCuenta;  // Identificador único de la cuenta
    private double saldo;  // Saldo actual de la cuenta

    // Constructor
    public Cuenta(TipoDeCuenta tipoDeCuenta, String numeroCuenta, double saldoInicial) {
        this.tipoDeCuenta = tipoDeCuenta;
        this.numeroCuenta = UUID.randomUUID();
        this.saldo = saldoInicial;
    }

    // Obtener el número de cuenta
    public UUID getNumeroCuenta() {
        return numeroCuenta;
    }

    // Obtener el saldo de la cuenta
    public double getSaldo() {
        return saldo;
    }

    // Depositar una cantidad en la cuenta
    public void depositar(double cantidad) {
        saldo += cantidad;
    }

    // Retirar una cantidad de la cuenta
    public void retirar(double cantidad) {
        saldo -= cantidad;
    }

    // Guardar la información de la cuenta en un archivo
    public void guardarCuenta(FileHandler fileHandler, String archivo, String idUsuario) throws IOException {
        String linea = this.numeroCuenta + "," + this.saldo + "," + idUsuario;
        fileHandler.guardarEnArchivo(archivo, linea);
    }
}