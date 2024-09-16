package org.example;

import java.io.IOException;
import java.util.*;

public class Homebanking {
    private Map<String, Usuario> usuarios = new HashMap<>();  // Mapa para almacenar usuarios por ID
    private Map<String, Cuenta> cuentas = new HashMap<>();  // Mapa para almacenar cuentas por ID
    private Usuario usuarioActual;  // Usuario actualmente autenticado
    private FileHandler fileHandler = new FileHandler();  // Instancia de FileHandler para manejar archivos
    private Scanner scanner = new Scanner(System.in);

    // Constructor
    public Homebanking() {}

    // Registrar un nuevo usuario en el sistema
    public void registrarUsuario() {
        System.out.println("Ingrese su DNI :");
        String id = scanner.nextLine();

        if (usuarios.containsKey(id)) {
            System.out.println("El usuario ya existe.");
            return;
        }

        System.out.println("Ingrese su nombre y apellido (como figura en el DNI):");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su dirección (como figura en el DNI):");
        String direccion = scanner.nextLine();

        System.out.println("Ingrese su teléfono:");
        String telefono = scanner.nextLine();

        System.out.println("Ingrese su email:");
        String email = scanner.nextLine();

        System.out.println("Ingrese su nombre de usuario:");
        String nombreUsuario = scanner.nextLine();

        System.out.println("Ingrese la contraseña:");
        String password = scanner.nextLine();

        // Corregido: incluir email en la creación del objeto Usuario
        Usuario usuario = new Usuario(nombre, direccion, telefono, email, nombreUsuario, password);

        // Corregido: el mapa debería usar el ID que has pedido al inicio (id, no dni)
        usuarios.put(id, usuario);

        // Guardar el usuario en el archivo
        usuario.guardarUsuario(fileHandler, "usuarios.txt");

        System.out.println("Usuario registrado: " + nombre);
    }

    // Iniciar sesión para el usuario
    public void iniciarSesion() throws IOException {
        System.out.println("Ingrese el nombre de usuario:");
        String nombreUsuario = scanner.nextLine();

        Usuario usuarioEncontrado = null;

        // Buscar el usuario por nombre de usuario
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                usuarioEncontrado = usuario;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.println("Ingrese la contraseña:");
        String password = scanner.nextLine();

        // Validar la contraseña
        if (!usuarioEncontrado.validarPassword(password)) {
            System.out.println("Contraseña incorrecta.");
            return;
        }

        usuarioActual = usuarioEncontrado;
        System.out.println("Sesión iniciada para: " + usuarioActual.getNombre());
        mostrarMenuCuenta();
    }


    // Menú principal del sistema
    public void menu() throws IOException {
        try {
            while (true) {
                System.out.println("\nMenú Principal:");
                System.out.println("1. Registrarse");
                System.out.println("2. Iniciar sesión");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();  // Consumir la nueva línea

                switch (opcion) {
                    case 1:
                        registrarUsuario();
                        break;
                    case 2:
                        iniciarSesion();
                        break;
                    case 3:
                        System.out.println("Saliendo del sistema.");
                        return;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            }
        } finally {
            scanner.close(); // Cerrar el scanner al finalizar
        }
    }

    public TipoDeCuenta elegirTipoDeCuenta(int tipoDeCuenta) {
        switch (tipoDeCuenta) {
            case 1:
                return TipoDeCuenta.CUENTA_CORRIENTE;
            case 2:
                return TipoDeCuenta.CAJA_DE_AHORRO;
            case 3:
                return TipoDeCuenta.PLAZO_FIJO;
            default:
                System.out.println("Opción inválida, intente nuevamente.");
        }
        return null;
    }

    // Abrir una nueva cuenta para el usuario actual
    private void abrirCuenta() throws IOException {
        if (usuarioActual == null) {
            System.out.println("Debe iniciar sesión antes de abrir una cuenta.");
            return;
        }

        System.out.println("1. " + TipoDeCuenta.CUENTA_CORRIENTE);
        System.out.println("2. " + TipoDeCuenta.CAJA_DE_AHORRO);
        System.out.println("3. " + TipoDeCuenta.PLAZO_FIJO);
        System.out.println("Ingrese tipo cuenta:");
        int tipoDeCuentaOpcion = Integer.parseInt(scanner.nextLine());
        TipoDeCuenta tipoDeCuenta = elegirTipoDeCuenta(tipoDeCuentaOpcion);

        if (tipoDeCuenta == null) {
            System.out.println("Tipo de cuenta inválido.");
            return;
        }

        System.out.println("Ingrese el DNI de la cuenta:");
        String cuentaId = scanner.nextLine();

        if (cuentas.containsKey(cuentaId)) {
            System.out.println("La cuenta ya existe.");
            return;
        }

        System.out.println("Ingrese el saldo inicial de la cuenta:");
        double saldoInicial;
        try {
            saldoInicial = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Monto inválido. Intente nuevamente.");
            return;
        }

        Cuenta cuenta = new Cuenta(tipoDeCuenta, cuentaId, saldoInicial);
        usuarioActual.agregarCuenta(cuenta);
        cuentas.put(cuentaId, cuenta);

        // Guardar la cuenta en el archivo
        cuenta.guardarCuenta(fileHandler, "cuentas.txt", String.valueOf(usuarioActual.getDni()));

        System.out.println("Cuenta abierta con DNI: " + cuentaId);
    }


    // Realizar una transacción entre dos cuentas
    private void realizarTransaccion() throws IOException {
        System.out.println("Ingrese el DNI de la cuenta origen:");
        String cuentaOrigenId = scanner.nextLine();

        System.out.println("Ingrese el DNI de la cuenta destino:");
        String cuentaDestinoId = scanner.nextLine();

        System.out.println("Ingrese el monto a transferir:");
        double cantidad = scanner.nextDouble();
        scanner.nextLine();  // Consumir la nueva línea

        Cuenta cuentaOrigen = cuentas.get(cuentaOrigenId);
        Cuenta cuentaDestino = cuentas.get(cuentaDestinoId);

        if (cuentaOrigen != null && cuentaDestino != null) {
            Transaccion.transfiera(cuentaOrigen, cuentaDestino, cantidad, fileHandler, "transacciones.txt");
            System.out.println("Transacción realizada: " + cantidad + " de " + cuentaOrigenId + " a " + cuentaDestinoId);
        } else {
            System.out.println("Una o ambas cuentas no existen.");
        }
    }
    // Método para mostrar el menú de inversiones
    private void Inversión() {
        Inversion inversion = new Inversion(scanner, usuarioActual);
        inversion.mostrarMenu();
    }

    // Mostrar el menú de opciones para el usuario actual
    private void mostrarMenuCuenta() throws IOException {
        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Abrir cuenta");
            System.out.println("2. Realizar transacción");
            System.out.println("3. Hace crecer tu dinero");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    abrirCuenta();
                    break;
                case 2:
                    realizarTransaccion();
                    break;
                case 3:
                    Inversión();
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
}