package pruebas;

import biblioteca.Libro;
import biblioteca.MaterialNoDisponibleException;
import biblioteca.Revista;
import biblioteca.Tesis;
import excepciones.Main;
import rentavehiculos.Automovil;
import rentavehiculos.Camion;
import rentavehiculos.VehiculoNoDisponibleException;

/**
 * Pruebas automáticas sin librerías externas.
 * Termina con código 1 si alguna prueba falla.
 */
public class Pruebas {

    private static int total = 0;
    private static int fallidas = 0;

    interface Prueba {
        void ejecutar() throws Exception;
    }

    static void prueba(String nombre, Prueba prueba) {
        total++;
        try {
            prueba.ejecutar();
            System.out.println("  OK     " + nombre);
        } catch (Throwable e) {
            fallidas++;
            System.out.println("  FALLÓ  " + nombre + " -> " + e);
        }
    }

    static void verificar(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError(mensaje);
        }
    }

    static void esperaExcepcion(Class<? extends Throwable> tipo, Prueba accion) {
        try {
            accion.ejecutar();
        } catch (Throwable e) {
            verificar(tipo.isInstance(e), "Se esperaba " + tipo.getSimpleName() + " pero fue " + e);
            return;
        }
        throw new AssertionError("Se esperaba " + tipo.getSimpleName() + " y no se lanzó nada");
    }

    public static void main(String[] args) {
        System.out.println("Biblioteca");
        prueba("un material nuevo está disponible", () -> {
            verificar(!new Libro("Java", "Juan", 2020, 100).isPrestado(), "debería estar disponible");
        });
        prueba("prestar dos veces lanza MaterialNoDisponibleException", () -> {
            Libro libro = new Libro("Java", "Juan", 2020, 100);
            libro.prestar();
            esperaExcepcion(MaterialNoDisponibleException.class, libro::prestar);
        });
        prueba("después de devolver se puede prestar otra vez", () -> {
            Revista revista = new Revista("Tech", "Ana", 2022, 3);
            revista.prestar();
            revista.devolver();
            revista.prestar();
            verificar(revista.isPrestado(), "debería estar prestada");
        });
        prueba("devolver algo que no está prestado falla", () -> {
            Tesis tesis = new Tesis("IA", "Luis", 2019, "UNSIJ");
            esperaExcepcion(IllegalStateException.class, tesis::devolver);
        });
        prueba("año negativo es inválido", () ->
                esperaExcepcion(biblioteca.DatoInvalidoException.class,
                        () -> new Revista("Tech", "Ana", -2022, 10)));
        prueba("año futuro es inválido", () ->
                esperaExcepcion(biblioteca.DatoInvalidoException.class,
                        () -> new Libro("Java", "Juan", 3000, 100)));
        prueba("título vacío es inválido", () ->
                esperaExcepcion(biblioteca.DatoInvalidoException.class,
                        () -> new Libro("  ", "Juan", 2020, 100)));

        System.out.println("\nRenta de vehículos");
        prueba("rentar dos veces lanza VehiculoNoDisponibleException", () -> {
            Automovil auto = new Automovil("Toyota", "Corolla", 2022, 4);
            auto.rentar();
            esperaExcepcion(VehiculoNoDisponibleException.class, auto::rentar);
        });
        prueba("devolver deja el vehículo disponible", () -> {
            Camion camion = new Camion("Volvo", "FH16", 2020, 18.5);
            camion.rentar();
            camion.devolver();
            verificar(camion.isDisponible(), "debería estar disponible");
        });
        prueba("año menor a 1900 es inválido", () ->
                esperaExcepcion(rentavehiculos.DatoInvalidoException.class,
                        () -> new Automovil("Nissan", "Sentra", 1800, 4)));
        prueba("un setter también valida", () -> {
            Automovil auto = new Automovil("Toyota", "Corolla", 2022, 4);
            esperaExcepcion(rentavehiculos.DatoInvalidoException.class, () -> auto.setNumeroPuertas(0));
            verificar(auto.getNumeroPuertas() == 4, "el valor anterior no debe cambiar");
        });

        System.out.println("\nCatálogo de excepciones");
        for (var caso : Main.CASOS) {
            prueba(caso.descripcion() + " lanza " + caso.esperada().getSimpleName(), () ->
                    esperaExcepcion(caso.esperada(), caso.accion()::ejecutar));
        }

        System.out.println("\n" + (total - fallidas) + " de " + total + " pruebas correctas");
        if (fallidas > 0) {
            System.exit(1);
        }
    }
}
