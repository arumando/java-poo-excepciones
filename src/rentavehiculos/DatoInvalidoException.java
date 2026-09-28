package rentavehiculos;

/** Se lanza cuando se intenta crear o modificar un vehículo con datos incorrectos. */
public class DatoInvalidoException extends Exception {

    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
