package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    // Guardar una línea de texto en un archivo
    public void guardarEnArchivo(String archivo, String linea) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(linea);
            writer.newLine(); // Agrega un salto de línea después de escribir la información
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Cargar líneas de texto desde un archivo
    public List<String> cargarDesdeArchivo(String archivo) {
        List<String> lineasDelArchivo = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineasDelArchivo.add(linea);  // Agrega cada línea del archivo a la lista
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return lineasDelArchivo;
    }
}