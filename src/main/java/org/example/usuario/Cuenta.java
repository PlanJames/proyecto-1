package org.example.usuario;

import org.example.FileHandler;

import java.io.BufferedReader;
import java.io.FileReader;
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

    // Método para guardar la cuenta en un archivo
    public void guardarCuenta(FileHandler fileHandler, String path, String dniUsuario) {
        // Verificar si la cuenta ya está guardada
        if (isCuentaExistente(path)) {
            System.out.println("La cuenta ya está guardada en el archivo.");
            return;  // No guarda si ya existe
        }

        String linea = dniUsuario + "," + tipoDeCuenta + "," + numeroCuenta + "," + saldo;
        fileHandler.guardarEnArchivo(path, linea);
        System.out.println("Cuenta guardada: " + linea);
    }

    // Método para verificar si la cuenta ya existe en el archivo
    private boolean isCuentaExistente(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String cuentaGuardada = partes[2];  // Suponiendo que el número de cuenta es la tercera parte
                if (cuentaGuardada.equals(this.numeroCuenta)) {
                    return true;  // La cuenta ya existe
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return false;  // No se encontró la cuenta
    }
}