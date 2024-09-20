package org.example.transaccion;


import org.example.usuario.Cuenta;
import org.example.FileHandler;

public class Transaccion {
    // Realizar una transferencia entre dos cuentas
    public static void transfiera(Cuenta origen, Cuenta destino, double cantidad, FileHandler fileHandler, String archivo) {
        if (origen.getSaldo() >= cantidad) {
            origen.retirar(cantidad);
            destino.depositar(cantidad);
            String linea = "Transferencia de " + cantidad + " de " + origen.getNumeroCuenta() + " a " + destino.getNumeroCuenta();
            fileHandler.guardarEnArchivo(archivo, linea);
            System.out.println("Transferencia guardada exitosamente.");
        } else {
            System.out.println("Fondos insuficientes en la cuenta de origen.");
        }
    }
}