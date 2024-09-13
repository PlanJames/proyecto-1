package org.example;

import java.io.IOException;
import java.util.List;

public class Usuario {
    private String nombre;
    private String id;  // Identificador único del usuario
    private String direccion;
    private String telefono;  // Teléfono del usuario
    private String email;
    private List<Cuenta> cuentas;  // Lista de cuentas asociadas al usuario

    // Constructor de la clase Usuario
    public Usuario(String nombre, String id, String direccion, String telefono, String email, List<Cuenta> cuentas) {
        this.nombre = nombre;
        this.id = id;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.cuentas = cuentas;
    }

    // Obtener el nombre del usuario
    public String getNombre() {
        return nombre;
    }

    // Obtener el ID del usuario
    public String getId() {
        return id;
    }

    // Obtener la lista de cuentas del usuario
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    // Agregar una cuenta a la lista de cuentas del usuario
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    // Guardar la información del usuario en un archivo
    public void guardarUsuario(FileHandler fileHandler, String archivo) throws IOException {
        String linea = this.nombre + "," + this.id + "," + this.direccion + "," + this.telefono + "," + this.email;
        fileHandler.guardarEnArchivo(archivo, linea);
    }
}





