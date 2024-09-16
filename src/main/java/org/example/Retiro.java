package org.example;
import java.util.Date;
public class Retiro {
    public class Extraccion extends Transaccion {
        public Extraccion(double monto, Date fecha) {
            super(monto, fecha);
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
