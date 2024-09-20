package org.example.usuario;

import org.example.FileHandler;

public class Transaccion {
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private double cantidad;
    private FileHandler fileHandler;
    private String archivo;

    // Constructor
    public Transaccion(Cuenta cuentaOrigen, Cuenta cuentaDestino, double cantidad, FileHandler fileHandler, String archivo) {
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.cantidad = cantidad;
        this.fileHandler = fileHandler;
        this.archivo = archivo;
    }

//    // Realizar la transferencia
//    public void realizarTransferencia() {
//        if (cuentaOrigen.getSaldo() >= cantidad) {
//            cuentaOrigen.retirar(cantidad);
//            cuentaDestino.depositar(cantidad);
//            String linea = "Transferencia de " + cantidad + " de " + cuentaOrigen.getNumeroCuenta() + " a " + cuentaDestino.getNumeroCuenta();
//            fileHandler.guardarEnArchivo(archivo, linea);
//            System.out.println("Transferencia realizada exitosamente.");
//        } else {
//            System.out.println("Fondos insuficientes en la cuenta de origen.");
//        }
//    }
}
