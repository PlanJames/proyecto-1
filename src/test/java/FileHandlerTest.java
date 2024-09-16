import org.example.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
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
    public void tearDown() {;
        if (testFile.exists()) testFile.delete();
    }

    @Test
    public void guardarEnArchivoTest() {
        String linea = "Esta es una línea de prueba.";
        fileHandler.guardarEnArchivo(testFile.getPath(), linea);

        // Verifica si el archivo contiene la línea que se guardó
        List<String> lineas = fileHandler.cargarDesdeArchivo(testFile.getPath());
        assertEquals(1, lineas.size());
        assertEquals(linea, lineas.getFirst());
    }

    @Test
    public void testCargarDesdeArchivo() {
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
    public void testCargarDesdeArchivoArchivoVacio() {
        // Verifica que el archivo vacío devuelve una lista vacía
        List<String> lineas = fileHandler.cargarDesdeArchivo(testFile.getPath());
        assert(lineas.isEmpty());
    }
}
