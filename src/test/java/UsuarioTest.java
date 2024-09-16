import org.example.Cuenta;
import org.example.TipoDeCuenta;
import org.example.Usuario;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UsuarioTest {

    @Test
    public void agregarCuentaTest() {
        Usuario usuario = new Usuario("Juan", "callefalsa 123", "1234125", "asda@asda.com");
        List<Cuenta> cuentas = new ArrayList<>();
        cuentas.addAll(usuario.getCuentas());
        usuario.agregarCuenta(new Cuenta(TipoDeCuenta.CUENTA_CORRIENTE, "123", 0.0));
        Assert.assertEquals(usuario.getCuentas().size(), cuentas.size()+1);

    }
}
