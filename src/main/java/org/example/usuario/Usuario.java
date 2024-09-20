package org.example.usuario;

import org.example.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private int dni;  // Identificador único del usuario
    private String direccion;
    private String telefono;  // Teléfono del usuario
    private String email;
    private String nombreUsuario;
    private String password;
    private List<Cuenta> cuentas = new ArrayList<>();  // Lista de cuentas asociadas al usuario

    // Constructor
    public Usuario(String nombre, int dni, String direccion, String telefono, String email, String nombreUsuario, String password) {
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    // Agregar una cuenta a la lista de cuentas del usuario
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    // Método para validar la contraseña
    public boolean validarPassword(String password) {
        return this.password.equals(password);
    }

    // Guardar la información del usuario en un archivo solo si no está cargado
    public void guardarUsuario(FileHandler fileHandler, String archivo) {
        try {
            // Verificar si el usuario ya está en el archivo
            List<String> usuariosExistentes = fileHandler.cargarDesdeArchivo(archivo);
            boolean usuarioExiste = usuariosExistentes.stream()
                    .anyMatch(linea -> {
                        try {
                            return Integer.parseInt(linea.split(",")[1]) == this.dni;
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            System.out.println("Error al procesar la línea del archivo: " + e.getMessage());
                            return false;
                        }
                    });

            if (usuarioExiste) {
                System.out.println("El usuario ya está registrado.");
            } else {
                // Concatenamos la información del usuario en una línea de texto
                String linea = this.nombre + "," + this.dni + "," + this.direccion + "," + this.telefono + "," +
                        this.email + "," + this.nombreUsuario + "," + this.password;

                // Guardamos la línea en el archivo
                fileHandler.guardarEnArchivo(archivo, linea);
                System.out.println("Usuario guardado exitosamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }
}