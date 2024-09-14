package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Usuario {
    private String nombre;
    private UUID id;  // Identificador único del usuario
    private String direccion;
    private String telefono;  // Teléfono del usuario
    private String email;
    private List<Cuenta> cuentas = new ArrayList<>();  // Lista de cuentas asociadas al usuario

    // Constructor
    public Usuario(String nombre, String direccion, String telefono, String email) {
        this.nombre = nombre;
        this.id = UUID.randomUUID();
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public UUID getId() {
        return id;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    // Agregar una cuenta a la lista de cuentas del usuario
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    // Guardar la información del usuario en un archivo
    public void guardarUsuario(FileHandler fileHandler, String archivo) {
        try {
            // Concatenamos la información del usuario en una línea de texto
            String linea = this.nombre + "," + this.id + "," + this.direccion + "," + this.telefono + "," + this.email;

            // Intentamos guardar la línea en el archivo
            fileHandler.guardarEnArchivo(archivo, linea);

            System.out.println("Usuario guardado exitosamente.");

        } catch (IOException e) {
            // Si ocurre un error, capturamos la excepción y mostramos un mensaje al usuario
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        }
    }
}