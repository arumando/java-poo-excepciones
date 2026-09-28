package rentavehiculos;

import java.time.Year;

/**
 * Vehículo que se puede rentar y devolver.
 *
 * La lógica de renta vive solo aquí; cada subclase únicamente dice
 * qué tipo de vehículo es y valida sus datos propios.
 */
public abstract class Vehiculo {

    private static final int ANIO_MINIMO = 1900;

    private String marca;
    private String modelo;
    private int anio;
    private boolean disponible;

    public Vehiculo(String marca, String modelo, int anio) throws DatoInvalidoException {
        setMarca(marca);
        setModelo(modelo);
        setAnio(anio);
        this.disponible = true;
    }

    /** Nombre del tipo de vehículo, por ejemplo "Automóvil". */
    public abstract String getTipo();

    /** Datos propios de la subclase, por ejemplo "Puertas: 4". */
    protected abstract String detalle();

    /** Renta el vehículo. Falla si ya estaba rentado. */
    public final void rentar() throws VehiculoNoDisponibleException {
        if (!disponible) {
            throw new VehiculoNoDisponibleException(
                    getTipo() + " " + marca + " " + modelo + " ya está rentado");
        }
        disponible = false;
    }

    /** Devuelve el vehículo. Falla si no estaba rentado. */
    public final void devolver() {
        if (disponible) {
            throw new IllegalStateException(
                    getTipo() + " " + marca + " " + modelo + " no está rentado");
        }
        disponible = true;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnio() {
        return anio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public final void setMarca(String marca) throws DatoInvalidoException {
        this.marca = validarTexto(marca, "La marca no puede estar vacía");
    }

    public final void setModelo(String modelo) throws DatoInvalidoException {
        this.modelo = validarTexto(modelo, "El modelo no puede estar vacío");
    }

    public final void setAnio(int anio) throws DatoInvalidoException {
        // Se permite el año siguiente porque los modelos nuevos salen antes
        int anioMaximo = Year.now().getValue() + 1;
        if (anio < ANIO_MINIMO || anio > anioMaximo) {
            throw new DatoInvalidoException(
                    "El año debe estar entre " + ANIO_MINIMO + " y " + anioMaximo + " (se recibió " + anio + ")");
        }
        this.anio = anio;
    }

    private static String validarTexto(String valor, String mensajeError) throws DatoInvalidoException {
        if (valor == null || valor.isBlank()) {
            throw new DatoInvalidoException(mensajeError);
        }
        return valor.trim();
    }

    public void mostrarInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return getTipo() + ": " + marca + " " + modelo
                + "\n  Año: " + anio
                + "\n  " + detalle()
                + "\n  Estado: " + (disponible ? "disponible" : "rentado");
    }
}
