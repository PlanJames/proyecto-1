package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private String id;  // El ID es un String
    private String direccion;
    private String telefono;  // El teléfono es un String
    private String email;
    private List<Cuenta> cuentas;  // Lista de cuentas asociadas

    // Constructor
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

    // Obtener las cuentas asociadas al usuario
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    // Método para agregar una cuenta al usuario
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    // Método para guardar la información del usuario en un archivo
    public void guardarEnArchivo(String archivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(this.nombre + "," + this.id + "," + this.direccion + "," + this.telefono + "," + this.email);
            writer.newLine();
        }
    }

    // Método para cargar usuarios desde un archivo
    public static void cargarDesdeLista(List<String> usuarios) {
//        for(String linea: usuarios) {
//            Usuario usuario = new Usuario(usuarios[0], id, usuarios[2], telefono, usuarios[4], new ArrayList<>());
//        }
//        return usuarios;
    }
}


