package rentavehiculos;

import java.util.List;

/** Demostración del sistema de renta de vehículos. */
public class Main {

    public static void main(String[] args) {
        try {
            Automovil auto = new Automovil("Toyota", "Corolla", 2022, 4);
            Motocicleta moto = new Motocicleta("Honda", "CBR", 2021, 600);
            Camion camion = new Camion("Volvo", "FH16", 2020, 18.5);

            System.out.println("=== FLOTILLA ===");
            for (Vehiculo vehiculo : List.of(auto, moto, camion)) {
                vehiculo.mostrarInfo();
            }

            System.out.println("\n=== RENTAS ===");
            auto.rentar();
            System.out.println("Rentado: " + auto.getMarca() + " " + auto.getModelo());

            auto.devolver();
            System.out.println("Devuelto: " + auto.getMarca() + " " + auto.getModelo());

            auto.rentar();
            System.out.println("Intentando rentarlo dos veces...");
            auto.rentar();
        } catch (VehiculoNoDisponibleException e) {
            System.out.println("No disponible: " + e.getMessage());
        } catch (DatoInvalidoException e) {
            System.out.println("Dato inválido: " + e.getMessage());
        }

        System.out.println("\n=== DATOS INVÁLIDOS ===");
        intentarCrear(() -> new Automovil("Nissan", "Sentra", 1800, 4));
        intentarCrear(() -> new Motocicleta("Yamaha", "R6", 2023, -500));
        intentarCrear(() -> new Camion("", "Actros", 2019, 20));
    }

    /** Operación que crea un vehículo y puede fallar por datos inválidos. */
    private interface Creacion {
        Vehiculo crear() throws DatoInvalidoException;
    }

    private static void intentarCrear(Creacion creacion) {
        try {
            creacion.crear();
        } catch (DatoInvalidoException e) {
            System.out.println("Dato inválido: " + e.getMessage());
        }
    }
}
