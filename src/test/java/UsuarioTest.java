import org.example.usuario.Cuenta;
import org.example.usuario.TipoDeCuenta;
import org.example.usuario.Usuario;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UsuarioTest {

    @Test
    public void agregarCuentaTest() {
        Usuario usuario = new Usuario("Juan", 12345623, "1234125", "asda@asda.com", "juanusuario", "jvolpin","password123");
        List<Cuenta> cuentas = new ArrayList<>();
        cuentas.addAll(usuario.getCuentas());
        usuario.agregarCuenta(new Cuenta(TipoDeCuenta.CUENTA_CORRIENTE, "123", 0.0));
        Assert.assertEquals(usuario.getCuentas().size(), cuentas.size()+1);

    }
}