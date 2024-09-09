package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class Cuenta {
    // Atributos
    private String numeroCuenta;
    private String titular;
    private double saldo;

    // Constructor
    public Cuenta(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    // Obtener el número de cuenta
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    // Obtener el titular de la cuenta
    public String getTitular() {
        return titular;
    }

    // Obtener el saldo actual
    public double getSaldo() {
        return saldo;
    }

    // Mostrar información de la cuenta
    public void mostrarInformacion() {
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: " + saldo);
    }

    // Método para guardar credenciales en un archivo de texto
    private static void guardarCredenciales(String dni, String password) {
        try {
            FileWriter escritor = new FileWriter("usuarios.txt", true); // "true" para añadir al archivo sin sobrescribir
            escritor.write("DNI: " + dni + ", Contraseña: " + password + "\n");
            escritor.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar las credenciales.");
            e.printStackTrace();
        }
    }

}
