package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    // Guardar una línea de texto en un archivo
    public void guardarEnArchivo(String archivo, String linea) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(linea);
            writer.newLine();
        }
    }

    // Cargar líneas de texto desde un archivo
    public List<String> cargarDesdeArchivo(String archivo) throws IOException {
        List<String> lineasDelArchivo = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineasDelArchivo.add(linea);
            }
        }
        return lineasDelArchivo;
    }
}



