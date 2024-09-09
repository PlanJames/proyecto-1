package org.example;

public class Transaccion {
    // Método estático para realizar una transferencia entre cuentas
    public static void transfiera(Cuenta origen, Cuenta destino, double cantidad) {
        if (origen.getSaldo() >= cantidad) {
            origen.retirar(cantidad);
            destino.depositar(cantidad);
        } else {
            System.out.println("Fondos insuficientes en la cuenta de origen.");
        }
    }
}

