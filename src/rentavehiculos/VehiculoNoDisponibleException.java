package rentavehiculos;

/** Se lanza cuando se intenta rentar un vehículo que ya está rentado. */
public class VehiculoNoDisponibleException extends Exception {

    public VehiculoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
