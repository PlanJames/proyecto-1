package org.example;
import java.util.Date;
public class MLTransaccion {

    public abstract class Transaccion {
        private double monto;
        private Date fecha;

        public Transaccion(double monto, Date fecha) {
            this.monto = monto;
            this.fecha = fecha;
        }

        public double getMonto() {
            return monto;
        }

        public Date getFecha() {
            return fecha;
        }

        @Override
        public String toString() {
            return "Transaccion{" +
                    "monto=" + monto +
                    ", fecha=" + fecha +
                    '}';
        }
    }

}
