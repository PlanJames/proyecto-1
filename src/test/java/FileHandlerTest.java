import org.example.FileHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FileHandlerTest {
    FileHandler fileHandler;
    File testFile;

    @BeforeTest
    public void setUp() {
        fileHandler = new FileHandler();
        testFile = new File("testfile.txt");
    }

    @AfterTest
    public void tearDown() {
        if (testFile.exists() && !testFile.delete()) {
            System.err.println("No se pudo eliminar el archivo de prueba.");
        }
    }

    @Test
    public void guardarEnArchivoTest() {
        String linea = "Esta es una línea de prueba.";
        fileHandler.guardarEnArchivo(testFile.getPath(), linea);

        // Verifica si el archivo contiene la línea que se guardó
        List<String> lineas = fileHandler.cargarDesdeArchivo(testFile.getPath());
        assertEquals(1, lineas.size(), "El número de líneas en el archivo no es el esperado.");
        assertEquals(linea, lineas.get(0), "La línea leída no coincide con la línea esperada.");
    }

    public static class CuentaTest {

    }
}