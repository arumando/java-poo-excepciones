package biblioteca;

import java.util.List;

/** Demostración del sistema de préstamos de la biblioteca. */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== CATÁLOGO ===");
        try {
            List<Material> catalogo = List.of(
                    new Libro("Java básico", "Juan Pérez", 2020, 250),
                    new Revista("Tecnología Hoy", "Ana López", 2022, 10),
                    new Tesis("Sistemas inteligentes", "María Ruiz", 2021, "UNSIJ"));

            for (Material material : catalogo) {
                material.mostrarInfo();
            }

            System.out.println("\n=== PRÉSTAMOS ===");
            Material libro = catalogo.get(0);
            libro.prestar();
            System.out.println("Préstamo registrado: " + libro.getTitulo());

            System.out.println("Intentando prestarlo otra vez...");
            libro.prestar();
        } catch (MaterialNoDisponibleException e) {
            System.out.println("No disponible: " + e.getMessage());
        } catch (DatoInvalidoException e) {
            System.out.println("Dato inválido: " + e.getMessage());
        }

        System.out.println("\n=== DATOS INVÁLIDOS ===");
        intentarCrear(() -> new Revista("Tech", "Ana", -2022, 10));
        intentarCrear(() -> new Libro("", "Juan", 2020, 100));
        intentarCrear(() -> new Tesis("IA", "Luis", 2019, " "));
    }

    /** Operación que crea un material y puede fallar por datos inválidos. */
    private interface Creacion {
        Material crear() throws DatoInvalidoException;
    }

    private static void intentarCrear(Creacion creacion) {
        try {
            creacion.crear();
        } catch (DatoInvalidoException e) {
            System.out.println("Dato inválido: " + e.getMessage());
        }
    }
}
