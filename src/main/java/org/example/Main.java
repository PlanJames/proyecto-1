package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Homebanking sistema = new Homebanking();
        try {
            sistema.menu();  // Iniciar el menú del sistema
        } catch (IOException e) {
            System.out.println("Ocurrió un error al cargar los archivos: " + e.getMessage());
        }
    }
}