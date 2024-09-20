package org.example;

import org.example.inversion.Inversion;
import org.example.transaccion.Transaccion;
import org.example.usuario.Cuenta;
import org.example.usuario.TipoDeCuenta;
import org.example.usuario.Usuario;

import java.util.*;

public class Homebanking {
    private Map<Integer, Usuario> usuarios = new HashMap<>();  // Mapa para almacenar usuarios por ID
    private Map<String, Cuenta> cuentas = new HashMap<>();  // Mapa para almacenar cuentas por ID
    private Usuario usuarioActual;  // Usuario actualmente autenticado
    private FileHandler fileHandler = new FileHandler();  // Instancia de FileHandler para manejar archivos
    private Scanner scanner = new Scanner(System.in);

    // Constructor
    public Homebanking() {
    }

    // Registrar un nuevo usuario en el sistema
    public void registrarUsuario() {
        System.out.println("Ingrese su DNI :");
        int dni = Integer.parseInt(scanner.nextLine());

        if (usuarios.containsKey(dni)) {
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

        // Creación del objeto Usuario
        Usuario usuario = new Usuario(nombre, dni, direccion, telefono, email, nombreUsuario, password);

        // El mapa usa el DNI
        usuarios.put(dni, usuario);

        // Guardar el usuario en el archivo
        usuario.guardarUsuario(fileHandler, "usuarios.txt");

        System.out.println("Usuario registrado: " + nombre);
    }

    // Iniciar sesión para el usuario
    public void iniciarSesion() {
        try {
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
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
            e.printStackTrace(); // Opcional: Para depuración
        }
    }

    // Menú principal del sistema
    public void menu() {
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
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            scanner.nextLine(); // Limpiar el buffer del scanner
        } catch (Exception e) {
            System.out.println("Error en el sistema: " + e.getMessage());
            e.printStackTrace(); // Para depuración
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
    private void abrirCuenta() {
        if (usuarioActual == null) {
            System.out.println("Debe iniciar sesión antes de abrir una cuenta.");
            return;
        }

        // Elegir tipo de cuenta
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

        // Verificar si el usuario ya tiene una cuenta de este tipo
        List<Cuenta> cuentasUsuario = usuarioActual.getCuentas();
        for (Cuenta cuenta : cuentasUsuario) {
            if (cuenta.getTipoDeCuenta() == tipoDeCuenta) {
                System.out.println("Ya tienes una cuenta de tipo " + tipoDeCuenta + ".");
                return;  // Salir si ya tiene una cuenta del mismo tipo
            }
        }

        // Solicitar saldo inicial
        System.out.println("Ingrese el saldo inicial de la cuenta:");
        double saldoInicial;
        try {
            saldoInicial = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Monto inválido. Intente nuevamente.");
            return;
        }

        // Crear la cuenta
        Cuenta cuenta = new Cuenta(tipoDeCuenta, saldoInicial);

        // Agregar la cuenta al usuario actual
        usuarioActual.agregarCuenta(cuenta);

        // Guardar la cuenta en el archivo, usando el DNI del usuario actual
        cuenta.guardarCuenta(fileHandler, "cuentas.txt", String.valueOf(usuarioActual.getDni()));

        System.out.println("Cuenta abierta exitosamente con saldo inicial de: $" + saldoInicial);
    }
    

    // Realizar una transacción entre dos cuentas
    private void realizarTransaccion() {
        // Comprobar que el usuario esté logueado
        if (usuarioActual == null) {
            System.out.println("No hay un usuario logueado. Inicie sesión primero.");
            return;
        }

        // Mostrar opciones para elegir el tipo de cuenta de origen
        System.out.println("Seleccione el tipo de cuenta desde la cual quiere realizar la transacción:");
        System.out.println("1. " + TipoDeCuenta.CUENTA_CORRIENTE);
        System.out.println("2. " + TipoDeCuenta.CAJA_DE_AHORRO);
        System.out.println("Ingrese el número correspondiente al tipo de cuenta:");

        // Leer la opción del usuario
        int tipoSeleccionado = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        // Obtener las cuentas del usuario actual
        List<Cuenta> cuentasUsuario = usuarioActual.getCuentas();
        Cuenta cuentaOrigen = null;

        switch (tipoSeleccionado) {
            case 1:
                // Buscar cuenta de tipo Cuenta Corriente
                for (Cuenta cuenta : cuentasUsuario) {
                    if (cuenta.getTipoDeCuenta() == TipoDeCuenta.CUENTA_CORRIENTE) {
                        cuentaOrigen = cuenta;
                        break;
                    }
                }
                break;
            case 2:
                // Buscar cuenta de tipo Caja de Ahorro
                for (Cuenta cuenta : cuentasUsuario) {
                    if (cuenta.getTipoDeCuenta() == TipoDeCuenta.CAJA_DE_AHORRO) {
                        cuentaOrigen = cuenta;
                        break;
                    }
                }
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        // Validar si se encontró la cuenta origen
        if (cuentaOrigen == null) {
            System.out.println("No tiene cuentas disponibles del tipo seleccionado.");
            return;
        }

        // Ingresar el ID de la cuenta destino
        System.out.println("Ingrese el ID de la cuenta destino (UUID):");
        System.out.println("UUIDs disponibles: " + cuentas.keySet());
        String cuentaDestinoId = scanner.nextLine();

        // Buscar la cuenta destino por UUID
        Cuenta cuentaDestino = cuentas.get(cuentaDestinoId);

        // Validar existencia de la cuenta destino
        if (cuentaDestino == null) {
            System.out.println("La cuenta destino no existe.");
            return;
        }

        // Solicitar el monto de la transacción
        System.out.println("Ingrese el monto a transferir:");
        double cantidad = scanner.nextDouble();
        scanner.nextLine();  // Consumir la nueva línea

        // Validar que el monto sea mayor a cero
        if (cantidad <= 0) {
            System.out.println("El monto debe ser mayor a cero.");
            return;
        }

        // Validar que la cuenta origen tenga suficiente saldo
        if (cuentaOrigen.getSaldo() < cantidad) {
            System.out.println("Fondos insuficientes en la cuenta de origen.");
            return;
        }

        // Confirmar la transacción
        System.out.println("¿Confirma la transferencia de " + cantidad + " a la cuenta destino " + cuentaDestinoId + "? (s/n)");
        String confirmacion = scanner.nextLine();
        if (!confirmacion.equalsIgnoreCase("s")) {
            System.out.println("Transacción cancelada.");
            return;
        }

        // Realizar la transacción
        try {
            Transaccion.transfiera(cuentaOrigen, cuentaDestino, cantidad, fileHandler, "transacciones.txt");
            System.out.println("Transacción realizada: " + cantidad + " de " + cuentaOrigen.getNumeroCuenta() + " a " + cuentaDestinoId);
        } catch (Exception e) {
            System.out.println("Error al realizar la transacción: " + e.getMessage());
        }
    }

    // Mostrar el menú de inversiones
    private void Inversion() {
        Inversion inversion = new Inversion(scanner, usuarioActual);
        inversion.mostrarMenu();
    }

    // Mostrar el saldo de las cuentas del usuario actual
    private void mostrarSaldo() {
        if (usuarioActual == null) {
            System.out.println("Debe iniciar sesión para ver el saldo.");
            return;
        }

        List<Cuenta> cuentasUsuario = usuarioActual.getCuentas();

        if (cuentasUsuario.isEmpty()) {
            System.out.println("No tiene cuentas registradas.");
        } else {
            System.out.println("Saldos de sus cuentas:");
            for (Cuenta cuenta : cuentasUsuario) {
                System.out.println("Cuenta: " + usuarioActual.getDni() + " | Saldo: $" + cuenta.getSaldo());
            }
        }
    }

    // Mostrar el menú de opciones para el usuario actual
    private void mostrarMenuCuenta() {
        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Abrir cuenta");
            System.out.println("2. Realizar transacción");
            System.out.println("3. Hace crecer tu dinero");
            System.out.println("4. Saldo en cuenta");
            System.out.println("5. Cerrar sesión");
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
                    Inversion();
                    break;
                case 4:
                    mostrarSaldo();
                    break;
                case 5:
                    usuarioActual = null;
                    System.out.println("Sesión cerrada.");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}