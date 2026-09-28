package biblioteca;

/** Se lanza cuando se intenta crear un material con datos incorrectos. */
public class DatoInvalidoException extends Exception {

    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
