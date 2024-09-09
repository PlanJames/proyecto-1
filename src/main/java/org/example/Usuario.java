package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private int id;
    private String direccion;
    private int telefono;
    private String email;
    private List<Cuenta> cuentas; // Lista de cuentas asociadas

    // Constructor
    public Usuario(String nombre, int id, String direccion, int telefono, String email, List<Cuenta> cuentas) {
        this.nombre = nombre;
        this.id = id;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.cuentas = cuentas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    // Método para guardar la información del usuario en un archivo
    public void guardarEnArchivo(String archivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(this.nombre + "," + this.id + "," + this.direccion + "," + this.telefono + "," + this.email);
            writer.newLine();
        }
    }

    // Método para cargar usuarios desde un archivo
    public static List<Usuario> cargarDesdeArchivo(String archivo) throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Suponiendo que cada línea está en el formato: nombre,id,direccion,telefono,email
                String[] datos = linea.split(",");
                // Convertir id y telefono de String a int
                int id = Integer.parseInt(datos[1]);
                int telefono = Integer.parseInt(datos[3]);
                Usuario usuario = new Usuario(datos[0], id, datos[2], telefono, datos[4], new ArrayList<>());
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}
