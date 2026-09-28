package rentavehiculos;

public class Motocicleta extends Vehiculo {

    private int cilindrada;

    public Motocicleta(String marca, String modelo, int anio, int cilindrada) throws DatoInvalidoException {
        super(marca, modelo, anio);
        setCilindrada(cilindrada);
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public final void setCilindrada(int cilindrada) throws DatoInvalidoException {
        if (cilindrada <= 0) {
            throw new DatoInvalidoException("La cilindrada debe ser mayor a 0");
        }
        this.cilindrada = cilindrada;
    }

    @Override
    public String getTipo() {
        return "Motocicleta";
    }

    @Override
    protected String detalle() {
        return "Cilindrada: " + cilindrada + " cc";
    }
}
