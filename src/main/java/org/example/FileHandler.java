package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    // Método para obtener la ruta del archivo en una carpeta de datos fuera de resources
    private String getFilePath(String archivo) {
        return new File("src/main/data/" + archivo).getAbsolutePath(); // Almacena en una carpeta separada
    }

    // Guardar una línea de texto en un archivo
    public void guardarEnArchivo(String archivo, String linea) {
        String rutaArchivo = getFilePath(archivo);  // Obtener la ruta de almacenamiento
        if (linea == null || linea.isEmpty()) {
            System.out.println("Error: línea vacía o nula. No se guardará.");
            return; // Evitar guardar líneas vacías o nulas
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write(linea);
            writer.newLine(); // Agrega un salto de línea después de escribir la información
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Cargar líneas de texto desde un archivo
    public List<String> cargarDesdeArchivo(String archivo) {
        String rutaArchivo = getFilePath(archivo);  // Obtener la ruta de almacenamiento
        List<String> lineasDelArchivo = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Verificar si la línea tiene el formato adecuado (por ejemplo, dos partes separadas por una coma)
                String[] partes = linea.split(","); // Cambia "," según tu separador
                if (partes.length < 2) {  // Ajusta este número según las partes esperadas en cada línea
                    System.out.println("Error al procesar la línea del archivo: " + linea);
                    continue; // Saltar esta línea si no está bien formada
                }
                lineasDelArchivo.add(linea);  // Agrega cada línea bien formada a la lista
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return lineasDelArchivo;
    }
}
