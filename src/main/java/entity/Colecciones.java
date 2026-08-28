package entity;

import java.util.Objects;

public class Colecciones {

    private String nombre;
    private String autor;
    private int totalVolumenes;
    private int totalPoseidos;
    private String estadoColeccion;
    private String estadoublicacion;

    public Colecciones(String nombre, String autor, int totalVolumenes, int totalPoseidos, String estadoColeccion, String estadoublicacion) {
        this.nombre = nombre;
        this.autor = autor;
        this.totalVolumenes = totalVolumenes;
        this.totalPoseidos = totalPoseidos;
        this.estadoColeccion = estadoColeccion;
        this.estadoublicacion = estadoublicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getTotalVolumenes() {
        return totalVolumenes;
    }

    public void setTotalVolumenes(int totalVolumenes) {
        this.totalVolumenes = totalVolumenes;
    }

    public int getTotalPoseidos() {
        return totalPoseidos;
    }

    public void setTotalPoseidos(int totalPoseidos) {
        this.totalPoseidos = totalPoseidos;
    }

    public String getEstadoColeccion() {
        return estadoColeccion;
    }

    public void setEstadoColeccion(String estadoColeccion) {
        this.estadoColeccion = estadoColeccion;
    }

    public String getEstadoublicacion() {
        return estadoublicacion;
    }

    public void setEstadoublicacion(String estadoublicacion) {
        this.estadoublicacion = estadoublicacion;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Colecciones that = (Colecciones) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(autor, that.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, autor);
    }

    @Override
    public String toString() {
        return "Colecciones{" +
                "nombre='" + nombre + '\'' +
                ", autor='" + autor + '\'' +
                ", totalVolumenes=" + totalVolumenes +
                ", totalPoseidos=" + totalPoseidos +
                ", estadoColeccion='" + estadoColeccion + '\'' +
                ", estadoublicacion='" + estadoublicacion + '\'' +
                '}';
    }
}
