package org.example;

import java.util.List;
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

    // Guardar la información de la cuenta en un archivo si no existe
    public void guardarCuenta(FileHandler fileHandler, String archivo, String idUsuario) {
        // Concatenamos la información de la cuenta en una línea de texto
        String linea = this.numeroCuenta + "," + this.saldo + "," + idUsuario;

        // Leer las líneas existentes del archivo
        List<String> lineasExistentes = fileHandler.cargarDesdeArchivo(archivo);

        // Verificar si la cuenta ya existe en el archivo
        for (String lineaExistente : lineasExistentes) {
            // Extraer el UUID de la cuenta guardada
            String[] datosCuenta = lineaExistente.split(",");
            if (datosCuenta.length > 0 && datosCuenta[0].equals(this.numeroCuenta.toString())) {
                System.out.println("La cuenta ya existe y no se guardará nuevamente.");
                return;
            }
        }

        // Guardar la línea en el archivo
        fileHandler.guardarEnArchivo(archivo, linea);

        System.out.println("Cuenta guardada exitosamente.");
    }
}