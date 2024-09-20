package org.example;

public enum TipoDeCuenta {
    CUENTA_CORRIENTE("Cuenta Corriente", 1.5),
    CAJA_DE_AHORRO("Caja de ahorro", 3.0),
    PLAZO_FIJO("Plazo Fijo", 7.0);

    private final String tipoDeCuenta;
    private final double porcentajeDeInteres;

    TipoDeCuenta(String tipoDeCuenta, Double porcentajeDeInteres) {
        this.tipoDeCuenta = tipoDeCuenta;
        this.porcentajeDeInteres = porcentajeDeInteres;
    }

    public String getTipoDeCuenta() {
        return tipoDeCuenta;
    }

    public double getPorcentajeDeInteres() {
        return porcentajeDeInteres;
    }

    public String toString() {
        return "tipo de cuenta: " + tipoDeCuenta;
    }
}