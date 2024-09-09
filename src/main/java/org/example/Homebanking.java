package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;

public class Homebanking {
    private Map<String, Usuario> usuarios = new HashMap<>();
    private Map<String, Cuenta> cuentas = new HashMap<>();
    private Usuario usuarioActual;

    // Constructor
    public Homebanking() {}

    // Método para registrar un nuevo usuario
    public void registrarUsuario(Scanner scanner) {
        System.out.println("Ingrese el ID del usuario:");
        String id = scanner.nextLine();

        if (usuarios.containsKey(id)) {
            System.out.println("El usuario ya existe.");
            return;
        }

        System.out.println("Ingrese el nombre del usuario:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la dirección del usuario:");
        String direccion = scanner.nextLine();

        System.out.println("Ingrese el teléfono del usuario:");
        String telefono = scanner.nextLine();

        System.out.println("Ingrese el email del usuario:");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(nombre, id, direccion, telefono, email, new ArrayList<>());
        usuarios.put(id, usuario);
        System.out.println("Usuario registrado: " + nombre);
    }

    // Método para iniciar sesión
    public void iniciarSesion(Scanner scanner) {
        System.out.println("Ingrese el ID del usuario:");
        String id = scanner.nextLine();
        usuarioActual = usuarios.get(id);

        if (usuarioActual == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.println("Sesión iniciada para: " + usuarioActual.getNombre());
        mostrarMenuCuenta(scanner);
    }

    // Mostrar el menú de opciones para el usuario actual
    private void mostrarMenuCuenta(Scanner scanner) {
        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Abrir cuenta");
            System.out.println("2. Realizar transacción");
            System.out.println("3. Consultar saldo");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    abrirCuenta(scanner);
                    break;
                case 2:
                    realizarTransaccion(scanner);
                    break;
                case 3:
                    consultarSaldo(scanner);
                    break;
                case 4:
                    usuarioActual = null;
                    System.out.println("Sesión cerrada.");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    // Método para abrir una cuenta
    private void abrirCuenta(Scanner scanner) {
        System.out.println("Ingrese el ID de la cuenta:");
        String cuentaId = scanner.nextLine();

        if (cuentas.containsKey(cuentaId)) {
            System.out.println("La cuenta ya existe.");
            return;
        }

        System.out.println("Ingrese el saldo inicial de la cuenta:");
        double saldoInicial = scanner.nextDouble();
        scanner.nextLine(); // Consumir la nueva línea

        Cuenta cuenta = new Cuenta(cuentaId, saldoInicial);
        usuarioActual.agregarCuenta(cuenta);
        cuentas.put(cuentaId, cuenta);
        System.out.println("Cuenta abierta con ID: " + cuentaId);
    }

    // Método para realizar una transacción
    private void realizarTransaccion(Scanner scanner) {
        System.out.println("Ingrese el ID de la cuenta origen:");
        String cuentaOrigenId = scanner.nextLine();

        System.out.println("Ingrese el ID de la cuenta destino:");
        String cuentaDestinoId = scanner.nextLine();

        System.out.println("Ingrese el monto a transferir:");
        double cantidad = scanner.nextDouble();
        scanner.nextLine(); // Consumir la nueva línea

        Cuenta cuentaOrigen = cuentas.get(cuentaOrigenId);
        Cuenta cuentaDestino = cuentas.get(cuentaDestinoId);

        if (cuentaOrigen != null && cuentaDestino != null) {
            Transaccion.transfiera(cuentaOrigen, cuentaDestino, cantidad);
            System.out.println("Transacción realizada: " + cantidad + " de " + cuentaOrigenId + " a " + cuentaDestinoId);
        } else {
            System.out.println("Una o ambas cuentas no existen.");
        }
    }

    // Método para consultar el saldo de una cuenta
    private void consultarSaldo(Scanner scanner) {
        System.out.println("Ingrese el ID de la cuenta:");
        String cuentaId = scanner.nextLine();
        Cuenta cuenta = cuentas.get(cuentaId);

        if (cuenta != null) {
            System.out.println("Saldo de la cuenta " + cuentaId + ": " + cuenta.getSaldo());
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    // Mostrar el menú principal
    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    registrarUsuario(scanner);
                    break;
                case 2:
                    iniciarSesion(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo del sistema.");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}



