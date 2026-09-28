package biblioteca;

public class Revista extends Material {

    private final int numeroEdicion;

    public Revista(String titulo, String autor, int anio, int numeroEdicion) throws DatoInvalidoException {
        super(titulo, autor, anio);
        if (numeroEdicion <= 0) {
            throw new DatoInvalidoException("El número de edición debe ser mayor a 0");
        }
        this.numeroEdicion = numeroEdicion;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    @Override
    public String getTipo() {
        return "Revista";
    }

    @Override
    protected String detalle() {
        return "Edición: " + numeroEdicion;
    }
}
