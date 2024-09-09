package org.example.registro.de;
import java.util.HashMap;
import java.util.Map;

public class SistemaBancario {
    private Map<String, usuario> usuarios;


    public void SistemaBancario() {
        usuarios = new HashMap<>();
    }

    public boolean registrarUsuario(String usuarioId, String nombre, String correo) {
        if (usuarios.containsKey(usuarioId)) {
            return false;
        }
        usuario nuevoUsuario = new usuario(usuarioId, nombre, correo);
        usuarios.put(usuarioId, nuevoUsuario);
        return true;
    }

    public boolean abrirCuenta(String usuarioId, String cuentaId, double saldoInicial) {
        usuario usuario = usuarios.get(usuarioId);
        if (usuario == null) {
            return false;
        }
        Cuenta nuevaCuenta = new Cuenta(cuentaId, saldoInicial);
        return usuario.agregarCuentas(nuevaCuenta);
    }

    public boolean realizarTransaccion(String usuarioId, String cuentaOrigenId, String cuentaDestinoId, double monto) {
        usuario usuario = usuarios.get(usuarioId);
        if (usuario == null) {
            return false;
        }
        Cuenta cuentaOrigen = usuario.obtenerCuenta(cuentaOrigenId);
        Cuenta cuentaDestino = usuario.obtenerCuenta(cuentaDestinoId);

        if (cuentaOrigen == null || cuentaDestino == null) {
            return false;
        }
        if (cuentaOrigen.retirar(monto)) {
            cuentaDestino.depositar(monto);
            return true;
        }
        return false;
    }

    public Double consultarSaldo(String usuarioId, String cuentaId) {
        usuario usuario = usuarios.get(usuarioId);
        if (usuario == null) {
            return null;
        }
        Cuenta cuenta = usuario.obtenerCuenta(cuentaId);
        if (cuenta == null) {
            return null;
        }
        return cuenta.getSaldo();
    }

    public void main(String[] args) {
        SistemaBancario sistema = new SistemaBancario();

        sistema.registrarUsuario("1", "Alice", "alice@gmail.com");
        sistema.registrarUsuario("2", "Bob", "bob@gmail.com");

        sistema.abrirCuenta("1", "cuenta_ahorros", 1000);
        sistema.abrirCuenta("1", "cuenta_corriente", 500);
        sistema.abrirCuenta("2", "cuenta_ahorros", 2000);


        boolean exito = sistema.realizarTransaccion("1", "cuenta_ahorros", "cuenta_corriente", 200);
        if (exito) {
            System.out.println("Transaccion realizada con exito.");
        } else {
            System.out.println("Error en la transaccion.");
        }

        System.out.println("Saldo cuenta_ahorros de Alice: " + sistema.consultarSaldo("1", "cuenta_ahorros"));
        System.out.println("Saldo cuenta_corriente de Alice: " + sistema.consultarSaldo("1", "cuenta_corriente"));
    }
}