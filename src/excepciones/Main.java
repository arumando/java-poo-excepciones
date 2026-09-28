package excepciones;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.MissingResourceException;

/**
 * Ejecuta cada caso del catálogo, atrapa la excepción y verifica que
 * sea del tipo esperado. Si algún caso no lanza lo que debería, lo reporta.
 */
public class Main {

    /** Acción que se espera que lance una excepción. */
    public interface Accion {
        void ejecutar() throws Exception;
    }

    public record Caso(String descripcion, Class<? extends Exception> esperada, Accion accion) {
    }

    public static final List<Caso> CASOS = List.of(
            new Caso("División entre cero", ArithmeticException.class,
                    () -> CatalogoExcepciones.dividir(10, 0)),
            new Caso("Texto nulo", NullPointerException.class,
                    () -> CatalogoExcepciones.longitudTexto(null)),
            new Caso("Índice fuera del arreglo", ArrayIndexOutOfBoundsException.class,
                    () -> CatalogoExcepciones.leerPosicion(new int[3], 5)),
            new Caso("Índice fuera del String", StringIndexOutOfBoundsException.class,
                    () -> CatalogoExcepciones.caracterEn("Hola", 10)),
            new Caso("Texto que no es número", NumberFormatException.class,
                    () -> CatalogoExcepciones.convertirNumero("abc")),
            new Caso("Conversión de tipo inválida", ClassCastException.class,
                    () -> CatalogoExcepciones.convertirObjeto("Hola")),
            new Caso("Argumento inválido", IllegalArgumentException.class,
                    () -> CatalogoExcepciones.validarEdad(-3)),
            new Caso("Estado inválido", IllegalStateException.class,
                    () -> CatalogoExcepciones.cerrarConexionCerrada(false)),
            new Caso("Operación no soportada", UnsupportedOperationException.class,
                    () -> CatalogoExcepciones.agregarAListaInmutable("C")),
            new Caso("Arreglo de tamaño negativo", NegativeArraySizeException.class,
                    () -> CatalogoExcepciones.crearArreglo(-5)),
            new Caso("Error de seguridad", SecurityException.class,
                    CatalogoExcepciones::accederAreaRestringida),
            new Caso("Acceso ilegal (checked)", IllegalAccessException.class,
                    CatalogoExcepciones::accesoIlegal),
            new Caso("Tipo incorrecto en arreglo", ArrayStoreException.class,
                    CatalogoExcepciones::guardarEnArreglo),
            new Caso("Modificación concurrente", ConcurrentModificationException.class,
                    CatalogoExcepciones::borrarMientrasSeRecorre),
            new Caso("Recurso no encontrado", MissingResourceException.class,
                    () -> CatalogoExcepciones.buscarRecurso("saludo")));

    /** Ejecuta un caso y devuelve true si lanzó exactamente la excepción esperada. */
    static boolean probar(Caso caso) {
        try {
            caso.accion().ejecutar();
            System.out.println("  FALLÓ  " + caso.descripcion() + ": no se lanzó ninguna excepción");
            return false;
        } catch (Exception e) {
            boolean correcta = caso.esperada().isInstance(e);
            System.out.printf("  %s  %-30s -> %s%n",
                    correcta ? "OK   " : "FALLÓ", caso.descripcion(), e.getClass().getSimpleName());
            return correcta;
        }
    }

    public static void main(String[] args) {
        System.out.println("Catálogo de excepciones de Java\n");
        int correctos = 0;
        for (int i = 0; i < CASOS.size(); i++) {
            System.out.print((i + 1 < 10 ? " " : "") + (i + 1) + ".");
            if (probar(CASOS.get(i))) {
                correctos++;
            }
        }
        System.out.println("\nResultado: " + correctos + " de " + CASOS.size() + " casos correctos");
    }
}
