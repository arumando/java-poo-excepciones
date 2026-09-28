package biblioteca;

/** Se lanza cuando se intenta prestar un material que ya está prestado. */
public class MaterialNoDisponibleException extends Exception {

    public MaterialNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
