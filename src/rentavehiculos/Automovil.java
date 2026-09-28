package rentavehiculos;

public class Automovil extends Vehiculo {

    private int numeroPuertas;

    public Automovil(String marca, String modelo, int anio, int numeroPuertas) throws DatoInvalidoException {
        super(marca, modelo, anio);
        setNumeroPuertas(numeroPuertas);
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public final void setNumeroPuertas(int numeroPuertas) throws DatoInvalidoException {
        if (numeroPuertas <= 0) {
            throw new DatoInvalidoException("El número de puertas debe ser mayor a 0");
        }
        this.numeroPuertas = numeroPuertas;
    }

    @Override
    public String getTipo() {
        return "Automóvil";
    }

    @Override
    protected String detalle() {
        return "Puertas: " + numeroPuertas;
    }
}
