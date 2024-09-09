package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    // Método para cargar usuarios desde un archivo
    public List<String> cargarDesdeArchivo(String archivo) throws IOException {
        List<String> lineasDelArchivo = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
            }
        }
        return lineasDelArchivo;
    }
}
