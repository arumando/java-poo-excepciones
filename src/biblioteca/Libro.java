package biblioteca;

public class Libro extends Material {

    private final int numeroPaginas;

    public Libro(String titulo, String autor, int anio, int numeroPaginas) throws DatoInvalidoException {
        super(titulo, autor, anio);
        if (numeroPaginas <= 0) {
            throw new DatoInvalidoException("El número de páginas debe ser mayor a 0");
        }
        this.numeroPaginas = numeroPaginas;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    @Override
    public String getTipo() {
        return "Libro";
    }

    @Override
    protected String detalle() {
        return "Páginas: " + numeroPaginas;
    }
}
