package rentavehiculos;

public class Camion extends Vehiculo {

    private double capacidadCarga;

    public Camion(String marca, String modelo, int anio, double capacidadCarga) throws DatoInvalidoException {
        super(marca, modelo, anio);
        setCapacidadCarga(capacidadCarga);
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public final void setCapacidadCarga(double capacidadCarga) throws DatoInvalidoException {
        if (capacidadCarga <= 0) {
            throw new DatoInvalidoException("La capacidad de carga debe ser mayor a 0");
        }
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public String getTipo() {
        return "Camión";
    }

    @Override
    protected String detalle() {
        return "Capacidad de carga: " + capacidadCarga + " toneladas";
    }
}
