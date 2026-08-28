package entity;

import java.util.Objects;

public class Libro {

    private int numeroVolumen;
    private String editorial;
    private String lenguaje;
    private String estadoLibro;
    private int coleccion;

    public Libro(int numeroVolumen, String editorial, String lenguaje, String estadoLibro, int coleccion) {
        this.numeroVolumen = numeroVolumen;
        this.editorial = editorial;
        this.lenguaje = lenguaje;
        this.estadoLibro = estadoLibro;
        this.coleccion = coleccion;
    }

    public int getNumeroVolumen() {
        return numeroVolumen;
    }

    public void setNumeroVolumen(int numeroVolumen) {
        this.numeroVolumen = numeroVolumen;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public String getEstadoLibro() {
        return estadoLibro;
    }

    public void setEstadoLibro(String estadoLibro) {
        this.estadoLibro = estadoLibro;
    }

    public int getColeccion() {
        return coleccion;
    }

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
        return "Libro{" +
                "numeroVolumen=" + numeroVolumen +
                ", editorial='" + editorial + '\'' +
                ", lenguaje='" + lenguaje + '\'' +
                ", estadoLibro='" + estadoLibro + '\'' +
                ", coleccion='" + coleccion + '\'' +
                '}';
    }
}
