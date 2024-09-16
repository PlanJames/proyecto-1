package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Usuario {
    private String nombre;
    private UUID dni;  // Identificador único del usuario
    private String direccion;
    private String telefono;  // Teléfono del usuario
    private String email;
    private String nombreUsuario;
    private String password;
    private List<Cuenta> cuentas = new ArrayList<>();  // Lista de cuentas asociadas al usuario

    // Constructor
    public Usuario(String nombre, String direccion, String telefono, String email, String nombreUsuario, String password) {
        this.nombre = nombre;
        this.dni = UUID.randomUUID();
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public UUID getDni() {
        return dni;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    // Agregar una cuenta a la lista de cuentas del usuario
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    // Método para validar la contraseña
    public boolean validarPassword(String password) {
        return this.password.equals(password);
    }

    // Guardar la información del usuario en un archivo
    public void guardarUsuario(FileHandler fileHandler, String archivo) {
        // Concatenamos la información del usuario en una línea de texto
        String linea = this.nombre + "," + this.dni + "," + this.direccion + "," + this.telefono + "," + this.email + "," + this.password + "," + this.nombreUsuario;

        // Guardamos la línea en el archivo
        fileHandler.guardarEnArchivo(archivo, linea);

        System.out.println("Usuario guardado exitosamente.");
    }
}
