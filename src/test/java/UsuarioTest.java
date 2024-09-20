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

        // Obtener el tamaño inicial de la lista de cuentas
        List<Cuenta> cuentas = new ArrayList<>(usuario.getCuentas());

        // Agregar una nueva cuenta al usuario (sin especificar el número de cuenta)
        usuario.agregarCuenta(new Cuenta(TipoDeCuenta.CUENTA_CORRIENTE, 0.0));  // UUID se genera automáticamente

        // Verificar que el tamaño de la lista de cuentas ha aumentado en 1
        Assert.assertEquals(usuario.getCuentas().size(), cuentas.size() + 1);
    }
}
