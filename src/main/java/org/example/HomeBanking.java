package org.example;
import java.util.HashMap;
import java.util.Map;

public class Homebanking {
    private Map<String, Usuario> usuarios;
    private Map<String, Cuenta> cuentas;

    public Homebanking() {
        usuarios = new HashMap<>();
        cuentas = new HashMap<>();
    }

    public void registrarUsuario(String id, String nombre, String direccion, String telefono, String mail) {
        if (!usuarios.containsKey(id)) {
            Usuario usuario = new Usuario(id, nombre, direccion, telefono, mail);
            usuarios.put(id, usuario);
            System.out.println("Usuario registrado: " + nombre);
        } else {
            System.out.println("El usuario ya existe.");
        }
    }

    public void abrirCuenta(String usuarioId, String cuentaId, double saldoInicial) {
        Usuario usuario = usuarios.get(usuarioId);
        if (usuario != null) {
            Cuenta cuenta = new Cuenta(cuentaId, saldoInicial);
            usuario.agregarCuenta(cuenta);
            cuentas.put(cuentaId, cuenta);
            System.out.println("Cuenta abierta con ID: " + cuentaId);
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    public void realizarTransaccion(String cuentaOrigenId, String cuentaDestinoId, double cantidad) {
        Cuenta cuentaOrigen = cuentas.get(cuentaOrigenId);
        Cuenta cuentaDestino = cuentas.get(cuentaDestinoId);

        if (cuentaOrigen != null && cuentaDestino != null) {
            Transaccion.transfiera(cuentaOrigen, cuentaDestino, cantidad);
            System.out.println("Transacción realizada: " + cantidad + " de " + cuentaOrigenId + " a " + cuentaDestinoId);
        } else {
            System.out.println("Una o ambas cuentas no existen.");
        }
    }

    public void consultarSaldo(String cuentaId) {
        Cuenta cuenta = cuentas.get(cuentaId);
        if (cuenta != null) {
            System.out.println("Saldo de la cuenta " + cuentaId + ": " + cuenta.getSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }
}

