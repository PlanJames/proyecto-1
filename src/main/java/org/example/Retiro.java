package org.example;
import java.util.Date;
public class Retiro {
    public class Extraccion extends MLTransaccion {
        public Extraccion(double monto, Date fecha, TipoDeCuenta tipoDeCuenta) {
            super(monto, fecha, tipoDeCuenta);
        }

        @Override
        public String toString() {
            return "Extraccion{" +
                    "monto=" + Retiro.this.getClass() +
                    ", fecha=" + getClass() +
                    '}';
        }
    }

}
