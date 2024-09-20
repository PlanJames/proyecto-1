import org.example.Homebanking;
import org.example.TipoDeCuenta;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomebankingTest {

    @Test
    public void abrirCuentaCorrienteTest() {
        Homebanking homebanking = new Homebanking();
        TipoDeCuenta tipoDeCuenta = homebanking.elegirTipoDeCuenta(1);
        Assert.assertEquals(TipoDeCuenta.CUENTA_CORRIENTE, tipoDeCuenta);
    }

    @Test
    public void abrirCajaDeAhorroTest() {
        Homebanking homebanking = new Homebanking();
        TipoDeCuenta tipoDeCuenta = homebanking.elegirTipoDeCuenta(2);
        Assert.assertEquals(TipoDeCuenta.CAJA_DE_AHORRO, tipoDeCuenta);
    }

    @Test
    public void abrirPlazoFijoTest() {
        Homebanking homebanking = new Homebanking();
        TipoDeCuenta tipoDeCuenta = homebanking.elegirTipoDeCuenta(3);
        Assert.assertEquals(TipoDeCuenta.PLAZO_FIJO, tipoDeCuenta);
    }
}