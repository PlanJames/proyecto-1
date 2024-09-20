package org.example.inversion;

public class Criptomoneda {
    private String nombre;
    private String simbolo;
    private double valorActual;  // Valor de la criptomoneda en USD

    public Criptomoneda(String nombre, String simbolo, double valorActual) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.valorActual = valorActual;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public double getValorActual() {
        return valorActual;
    }

    public void setValorActual(double valorActual) {
        this.valorActual = valorActual;
    }

    @Override
    public String toString() {
        return nombre + " (" + simbolo + ") - $" + valorActual;
    }
}
