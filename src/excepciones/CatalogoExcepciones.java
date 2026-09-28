package excepciones;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

/**
 * Métodos que provocan a propósito 15 excepciones comunes de Java,
 * para estudiar cuándo aparece cada una.
 */
public final class CatalogoExcepciones {

    private CatalogoExcepciones() {
    }

    // 1. Dividir un entero entre cero
    public static int dividir(int a, int b) {
        return a / b;
    }

    // 2. Usar un objeto que es null
    public static int longitudTexto(String texto) {
        return texto.length();
    }

    // 3. Acceder a una posición que no existe en un arreglo
    public static int leerPosicion(int[] arreglo, int indice) {
        return arreglo[indice];
    }

    // 4. Acceder a una posición que no existe en un String
    public static char caracterEn(String texto, int indice) {
        return texto.charAt(indice);
    }

    // 5. Convertir a número un texto que no es número
    public static int convertirNumero(String texto) {
        return Integer.parseInt(texto);
    }

    // 6. Convertir un objeto a un tipo que no le corresponde
    public static Integer convertirObjeto(Object objeto) {
        return (Integer) objeto;
    }

    // 7. Recibir un argumento que no tiene sentido
    public static void validarEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa: " + edad);
        }
    }

    // 8. Llamar a una operación en un momento en que no se permite
    public static void cerrarConexionCerrada(boolean abierta) {
        if (!abierta) {
            throw new IllegalStateException("La conexión ya estaba cerrada");
        }
    }

    // 9. Modificar una lista inmutable
    public static void agregarAListaInmutable(String elemento) {
        List<String> inmutable = List.of("A", "B");
        inmutable.add(elemento);
    }

    // 10. Crear un arreglo con tamaño negativo
    public static int[] crearArreglo(int tamanio) {
        return new int[tamanio];
    }

    // 11. Operación bloqueada por seguridad
    public static void accederAreaRestringida() {
        throw new SecurityException("Acceso denegado");
    }

    // 12. Excepción verificada (checked): el compilador obliga a atraparla
    public static void accesoIlegal() throws IllegalAccessException {
        throw new IllegalAccessException("Acceso ilegal");
    }

    // 13. Guardar un tipo incorrecto en un arreglo
    public static void guardarEnArreglo() {
        Object[] datos = new String[3];
        datos[0] = 100;
    }

    // 14. Modificar una lista mientras se recorre con for-each.
    //     Con solo 2 elementos NO falla: al borrar el primero, el ciclo
    //     termina antes de detectar el cambio. Con 3 o más sí falla.
    public static void borrarMientrasSeRecorre() {
        List<String> lista = new ArrayList<>(List.of("A", "B", "C"));
        for (String elemento : lista) {
            if (elemento.equals("A")) {
                lista.remove(elemento);
            }
        }
    }

    // 15. Buscar un recurso que no existe
    public static void buscarRecurso(String clave) {
        throw new MissingResourceException("Recurso no encontrado: " + clave, "Mensajes", clave);
    }
}
