package biblioteca;

public class Tesis extends Material {

    private final String universidad;

    public Tesis(String titulo, String autor, int anio, String universidad) throws DatoInvalidoException {
        super(titulo, autor, anio);
        if (universidad == null || universidad.isBlank()) {
            throw new DatoInvalidoException("La universidad no puede estar vacía");
        }
        this.universidad = universidad.trim();
    }

    public String getUniversidad() {
        return universidad;
    }

    @Override
    public String getTipo() {
        return "Tesis";
    }

    @Override
    protected String detalle() {
        return "Universidad: " + universidad;
    }
}
