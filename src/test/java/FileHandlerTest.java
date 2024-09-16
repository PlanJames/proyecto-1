package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileHandlerTest {

    private FileHandler fileHandler;
    private File testFile;

    @BeforeEach
    public void setUp() {
        fileHandler = new FileHandler();
        testFile = new File("testfile.txt");
    }

    @AfterEach
    public void tearDown() {
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testGuardarEnArchivo() throws IOException {
        String linea = "Esta es una línea de prueba.";
        fileHandler.guardarEnArchivo(testFile.getPath(), linea);

        // Verifica si el archivo contiene la línea que se guardó
        List<String> lineas = fileHandler.cargarDesdeArchivo(testFile.getPath());
        assertEquals(1, lineas.size());
        assertEquals(linea, lineas.get(0));
    }

    @Test
    public void testCargarDesdeArchivo() throws IOException {
        String linea1 = "Primera línea.";
        String linea2 = "Segunda línea.";
        fileHandler.guardarEnArchivo(testFile.getPath(), linea1);
        fileHandler.guardarEnArchivo(testFile.getPath(), linea2);

        // Cargar las líneas desde el archivo
        List<String> lineas = fileHandler.cargarDesdeArchivo(testFile.getPath());
        assertEquals(2, lineas.size());
        assertEquals(linea1, lineas.get(0));
        assertEquals(linea2, lineas.get(1));
    }

    @Test
    public void testCargarDesdeArchivoArchivoVacío() throws IOException {
        // Verifica que el archivo vacío devuelve una lista vacía
        List<String> lineas = fileHandler.cargarDesdeArchivo(testFile.getPath());
        assertTrue(lineas.isEmpty());
    }
}

