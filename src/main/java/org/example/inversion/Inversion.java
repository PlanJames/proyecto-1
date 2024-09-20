package org.example.inversion;

import org.example.usuario.Cuenta;
import org.example.usuario.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class Inversion {
    private Scanner scanner;
    private Usuario usuarioActual;

    public Inversion(Scanner scanner, Usuario usuarioActual) {
        this.scanner = scanner;
        this.usuarioActual = usuarioActual;
    }

    public void mostrarMenu() {
        System.out.println("Elija el tipo de inversión:");
        System.out.println("1. Fondo Común de Inversión");
        System.out.println("2. CEDEAR");
        System.out.println("3. Criptomonedas");

        int opcion = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        switch (opcion) {
            case 1:
                invertirEnFondoComun();
                break;
            case 2:
                invertirEnCedear();
                break;
            case 3:
                mostrarMenuCriptomonedas();
                break;
            default:
                System.out.println("Opción no válida. Por favor, elija una opción correcta.");
        }
    }

    private void invertirEnFondoComun() {
        System.out.println("Ha elegido invertir en Fondo Común de Inversión.");
        System.out.println("Ingrese el monto a invertir:");
        double monto = scanner.nextDouble();
        scanner.nextLine();  // Consumir la nueva línea

        // Verificar si el usuario tiene saldo suficiente
        if (tieneSaldoSuficiente(usuarioActual, monto)) {
            System.out.println("Monto disponible, continúe.");

            // Pedir la fecha de retiro
            System.out.println("Ingrese la fecha de retiro (formato dd/MM/yyyy):");
            String fechaRetiroStr = scanner.nextLine();

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaRetiro;

            try {
                // Parsear la fecha de retiro
                fechaRetiro = formatoFecha.parse(fechaRetiroStr);

                // Mostrar la fecha en el formato correcto (dd/MM/yyyy)
                String fechaFormateada = formatoFecha.format(fechaRetiro);
                System.out.println("Fecha de retiro ingresada: " + fechaFormateada);

                // Generar un rendimiento aleatorio
                Random random = new Random();
                double rendimiento = 15 + (25 * random.nextDouble());  // Genera un valor entre 15 y 40

                // Calcular el beneficio estimado
                double beneficioEstimado = monto * (rendimiento / 100);

                // Mostrar el rendimiento estimado
                System.out.println("El rendimiento estimado para su inversión es: " + String.format("%.2f", rendimiento) + "%");
                System.out.println("Podría generar un estimado de $" + String.format("%.2f", beneficioEstimado) + " al momento del retiro.");

            } catch (ParseException e) {
                System.out.println("Formato de fecha incorrecto. Por favor, ingrese una fecha válida.");
            }

        } else {
            System.out.println("Saldo insuficiente para realizar esta inversión.");
        }
    }

    private void invertirEnCedear() {
        System.out.println("Ha elegido invertir en CEDEAR.");
        System.out.println("Ingrese el símbolo o nombre de la empresa en la que desea invertir:");
        String entrada = scanner.nextLine();

        // Buscar la empresa en el archivo de CEDEARs
        String[] datosEmpresa = buscarEmpresaCedear(entrada);

        if (datosEmpresa != null) {
            String simbolo = datosEmpresa[0];
            String nombre = datosEmpresa[1];
            double valorPorAccion = Double.parseDouble(datosEmpresa[2]);

            System.out.println("Empresa encontrada: " + nombre + " (" + simbolo + ")");
            System.out.println("Valor por acción: $" + valorPorAccion);
            System.out.println("Ingrese la cantidad de acciones que desea comprar:");

            int cantidadAcciones = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea

            // Calcular el monto total necesario
            double montoCedear = cantidadAcciones * valorPorAccion;

            // Verificar si el usuario tiene saldo suficiente
            if (tieneSaldoSuficiente(usuarioActual, montoCedear)) {
                // Si la cantidad de acciones es al menos 1
                if (cantidadAcciones >= 1) {
                    System.out.println("Inversión realizada: Ha comprado " + cantidadAcciones + " acciones de " + nombre + " por un total de $" + montoCedear);

                    // Aquí no se actualiza el saldo
                } else {
                    System.out.println("La cantidad de acciones debe ser al menos 1.");
                }
            } else {
                System.out.println("Saldo insuficiente para realizar esta inversión.");
            }
        } else {
            System.out.println("La empresa ingresada no está disponible para comprar.");
        }
    }

    // Método para buscar una empresa en el archivo de CEDEARs y devolver los datos si la encuentra
    private String[] buscarEmpresaCedear(String entrada) {
        // Usar el recurso dentro del directorio resources
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("cedears.txt");
        if (inputStream == null) {
            System.out.println("No se encontró el archivo de CEDEARs.");
            return null;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String simbolo = partes[0].trim();
                String nombre = partes[1].trim();
                double valor = Double.parseDouble(partes[2].trim());

                // Verificar si el símbolo o el nombre coinciden con la entrada del usuario
                if (entrada.equalsIgnoreCase(simbolo) || entrada.equalsIgnoreCase(nombre)) {
                    return partes;  // Devolver los datos de la empresa (símbolo, nombre y valor)
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de CEDEARs.");
        }
        return null;
    }

    // Método auxiliar para verificar si el usuario tiene saldo suficiente en alguna cuenta
    private boolean tieneSaldoSuficiente(Usuario usuario, double monto) {
        for (Cuenta cuenta : usuario.getCuentas()) {
            if (cuenta.getSaldo() >= monto) {
                return true;  // El usuario tiene suficiente saldo en esta cuenta
            }
        }
        return false;  // No tiene suficiente saldo en ninguna cuenta
    }
    private static final double TASA_USD_ARS = 1250.0;  // Tasa de cambio fija para convertir USD a ARS

    private void mostrarMenuCriptomonedas() {
        System.out.println("Opciones para invertir en Criptomonedas:");
        System.out.println("1. Ver criptomonedas disponibles");
        System.out.println("2. Invertir en una criptomoneda");

        int opcion = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        switch (opcion) {
            case 1:
                mostrarCriptomonedasDisponibles();
                mostrarMenuCriptomonedas();  // Vuelve al menú de criptomonedas después de mostrar la lista
                break;
            case 2:
                invertirEnCriptomoneda();
                break;
            default:
                System.out.println("Opción no válida.");
                mostrarMenuCriptomonedas();  // Repetir el menú si la opción es inválida
                break;
        }
    }

    private void mostrarCriptomonedasDisponibles() {
        System.out.println("Criptomonedas disponibles para invertir:");

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("criptomonedas.txt");
        if (inputStream == null) {
            System.out.println("No se encontró el archivo de criptomonedas.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String simbolo = partes[0].trim();
                String nombre = partes[1].trim();
                String valorActual = partes[2].trim();
                System.out.println(simbolo + " - " + nombre + " - " + valorActual);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de criptomonedas.");
        }
    }


    private void invertirEnCriptomoneda() {
        System.out.println("Ha elegido invertir en Criptomonedas.");
        System.out.println("Ingrese el símbolo o nombre de la criptomoneda en la que desea invertir:");
        String entrada = scanner.nextLine();

        // Buscar la criptomoneda en el archivo o fuente de datos
        Criptomoneda criptomoneda = buscarCriptomoneda(entrada);

        if (criptomoneda != null) {
            // Obtener el valor de la criptomoneda en USD
            double valorEnUSD = criptomoneda.getValorActual();

            System.out.println("Criptomoneda encontrada: " + criptomoneda.getNombre() + " (" + criptomoneda.getSimbolo() + ")");
            System.out.println("Valor actual en dólares estadounidenses (USD): $" + valorEnUSD);
            System.out.println("Ingrese el monto a invertir en pesos argentinos (ARS):");

            double montoEnPesos = scanner.nextDouble();
            scanner.nextLine();  // Consumir la nueva línea

            // Convertir el monto de ARS a USD
            double montoEnUSD = montoEnPesos / TASA_USD_ARS;

            // Verificar si el usuario tiene saldo suficiente en pesos argentinos
            if (tieneSaldoSuficiente(usuarioActual, montoEnPesos)) {
                // Calcular cuántas criptomonedas puede comprar con el monto en USD
                double cantidadCriptomonedas = montoEnUSD / valorEnUSD;

                System.out.println("Inversión realizada: Ha comprado " + String.format("%.8f", cantidadCriptomonedas) + " " + criptomoneda.getSimbolo() + " por un total de $" + montoEnPesos + " ARS.");
                System.out.println("Monto equivalente en dólares: $" + String.format("%.2f", montoEnUSD) + " USD.");
                // Aquí puedes registrar la inversión (en pesos y criptomonedas) en un archivo o base de datos
            } else {
                System.out.println("Saldo insuficiente para realizar esta inversión.");
            }
        } else {
            System.out.println("La criptomoneda ingresada no está disponible.");
        }
    }


    private Criptomoneda buscarCriptomoneda(String entrada) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("criptomonedas.txt");
        if (inputStream == null) {
            System.out.println("No se encontró el archivo de criptomonedas.");
            return null;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String simbolo = partes[0].trim();
                String nombre = partes[1].trim();
                double valorActual = Double.parseDouble(partes[2].trim());

                // Verificar si el símbolo o el nombre coinciden con la entrada del usuario
                if (entrada.equalsIgnoreCase(simbolo) || entrada.equalsIgnoreCase(nombre)) {
                    return new Criptomoneda(nombre, simbolo, valorActual);  // Devolver la criptomoneda encontrada
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de criptomonedas.");
        }
        return null;
    }


}
