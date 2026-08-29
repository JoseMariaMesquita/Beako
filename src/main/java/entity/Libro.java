package entity;

import java.util.Objects;

public class Libro {

    private int idBook;
    private int numeroVolumen;
    private String editorial;
    private String lenguaje;
    private String estadoLibro;
    private int coleccion;

    /**
     * Constructor  of the class including its id
     * @param idBook - Identifier of the book
     * @param numeroVolumen - Number of the volume
     * @param editorial - Editorial that publish the book
     * @param lenguaje - Language of the book
     * @param estadoLibro - State of the book regarding user interaction with it ['stopped', 'finished', 'onreading']
     * @param coleccion - Collection from which the book is from
     */
    public Libro(int idBook, int numeroVolumen, String editorial, String lenguaje, String estadoLibro, int coleccion) {
        this.idBook = idBook;
        this.numeroVolumen = numeroVolumen;
        this.editorial = editorial;
        this.lenguaje = lenguaje;
        this.estadoLibro = estadoLibro;
        this.coleccion = coleccion;
    }

    /**
     * Constructor  of the class
     * @param numeroVolumen - Number of the volume
     * @param editorial - Editorial that publish the book
     * @param lenguaje - Language of the book
     * @param estadoLibro - State of the book regarding user interaction with it ['stopped', 'finished', 'onreading']
     * @param coleccion - Collection from which the book is from
     */
    public Libro(int numeroVolumen, String editorial, String lenguaje, String estadoLibro, int coleccion) {
        this.numeroVolumen = numeroVolumen;
        this.editorial = editorial;
        this.lenguaje = lenguaje;
        this.estadoLibro = estadoLibro;
        this.coleccion = coleccion;
    }

    /**
     * Gets the Identifier of the book
     * @return - Identifier of the book
     */
    public int getIdBook() {
        return idBook;
    }

    /**
     * Sets the identifier of the book
     * @param idBook - Identifier of the book
     */
    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    /**
     * Ges the number of the Volume
     * @return - Number of the book
     */
    public int getNumeroVolumen() {
        return numeroVolumen;
    }

    /**
     * Sets the number of the book
     * @param numeroVolumen - Number of the book
     */
    public void setNumeroVolumen(int numeroVolumen) {
        this.numeroVolumen = numeroVolumen;
    }

    /**
     * Gets the name of the editorial
     * @return - Name of the editorial
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * Sets the name of the editorial
     * @param editorial - Name of the editorial
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * Gets the languaje in which the bookis written on
     * @return - Language in which the book is written on
     */
    public String getLenguaje() {
        return lenguaje;
    }

    /**
     * Sets the language in which the book is written on allowing only three letters as max to name the language ex ESP, PT, ENG, JP
     * @param lenguaje - Language in which the book is written on allowing only three letters as max to name the language ex ESP, PT, ENG, JP
     */
    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    /**
     * Gets the state of the book regarding if the user has read it finished it or still reading it
     * @return - State of the book regarding user reading of it ['stopped', 'finished', 'onreading']
     */
    public String getEstadoLibro() {
        return estadoLibro;
    }

    /**
     * Sets the state of the book regarding if the user has read it finished it or still reading it
     * @param estadoLibro - State of the book regarding user reading of it ['stopped', 'finished', 'onreading']
     */
    public void setEstadoLibro(String estadoLibro) {
        this.estadoLibro = estadoLibro;
    }

    /**
     * Gets the ID of the collection from which the book is
     * @return - ID of the collection from which the book is
     */
    public int getColeccion() {
        return coleccion;
    }

    /**
     * Sets the ID of the collection from which the book is
     * @param coleccion - ID of the collection from which the book is
     */
    public void setColeccion(int coleccion) {
        this.coleccion = coleccion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return numeroVolumen == libro.numeroVolumen && Objects.equals(editorial, libro.editorial) && Objects.equals(lenguaje, libro.lenguaje) && Objects.equals(coleccion, libro.coleccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroVolumen, editorial, lenguaje, coleccion);
    }

    @Override
    public String toString() {
        return Integer.toString(this.numeroVolumen);
    }
}
