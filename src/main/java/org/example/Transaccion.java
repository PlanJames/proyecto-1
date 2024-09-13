package org.example;

import java.io.IOException;

public class Transaccion {
    // Realizar una transferencia entre dos cuentas
    public static void transfiera(Cuenta origen, Cuenta destino, double cantidad, FileHandler fileHandler, String archivo) throws IOException {
        if (origen.getSaldo() >= cantidad) {
            origen.retirar(cantidad);
            destino.depositar(cantidad);
            String linea = "Transferencia de " + cantidad + " de " + origen.getNumeroCuenta() + " a " + destino.getNumeroCuenta();
            fileHandler.guardarEnArchivo(archivo, linea);
        } else {
            System.out.println("Fondos insuficientes en la cuenta de origen.");
        }
    }
}




