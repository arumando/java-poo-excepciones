package biblioteca;

import java.time.Year;

/**
 * Material de una biblioteca que se puede prestar y devolver.
 *
 * La lógica de préstamo vive solo aquí; cada subclase únicamente dice
 * qué tipo de material es y qué datos propios tiene.
 */
public abstract class Material {

    private final String titulo;
    private final String autor;
    private final int anioPublicacion;
    private boolean prestado;

    public Material(String titulo, String autor, int anioPublicacion) throws DatoInvalidoException {
        if (titulo == null || titulo.isBlank()) {
            throw new DatoInvalidoException("El título no puede estar vacío");
        }
        if (autor == null || autor.isBlank()) {
            throw new DatoInvalidoException("El autor no puede estar vacío");
        }
        int anioActual = Year.now().getValue();
        if (anioPublicacion < 1 || anioPublicacion > anioActual) {
            throw new DatoInvalidoException(
                    "El año debe estar entre 1 y " + anioActual + " (se recibió " + anioPublicacion + ")");
        }
        this.titulo = titulo.trim();
        this.autor = autor.trim();
        this.anioPublicacion = anioPublicacion;
        this.prestado = false;
    }

    /** Nombre del tipo de material, por ejemplo "Libro". */
    public abstract String getTipo();

    /** Datos propios de la subclase, por ejemplo "Páginas: 250". */
    protected abstract String detalle();

    /** Presta el material. Falla si ya estaba prestado. */
    public final void prestar() throws MaterialNoDisponibleException {
        if (prestado) {
            throw new MaterialNoDisponibleException(
                    getTipo() + " \"" + titulo + "\" ya está en préstamo");
        }
        prestado = true;
    }

    /** Devuelve el material. Falla si no estaba prestado. */
    public final void devolver() {
        if (!prestado) {
            throw new IllegalStateException(
                    getTipo() + " \"" + titulo + "\" no está en préstamo");
        }
        prestado = false;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void mostrarInfo() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return getTipo() + ": " + titulo
                + "\n  Autor: " + autor
                + "\n  Año: " + anioPublicacion
                + "\n  " + detalle()
                + "\n  Estado: " + (prestado ? "prestado" : "disponible");
    }
}
