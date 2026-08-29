package entity;

import java.util.Objects;

public class Colecciones {

    private int idCollection;
    private String nombre;
    private String autor;
    private int totalVolumenes;
    private int totalPoseidos;
    private String estadoColeccion;
    private String estadoublicacion;

    /**
     * Constructor of the Colecciones Class with the idCollectionparameter
     * @param idCollection - Identifier of the Collection
     * @param nombre - Name of the collection
     * @param autor - Name of the author of the collection
     * @param totalVolumenes - Total amount of volumes of the Collection
     * @param totalPoseidos - Owned Books of this collection
     * @param estadoColeccion - State of the collection, if user keeps track of the collection or has stop gathering that collection
     * @param estadoublicacion - State of the publication of the collection, if they are still publishing it or have stopped
     */
    public Colecciones(int idCollection, String nombre, String autor, int totalVolumenes, int totalPoseidos, String estadoColeccion, String estadoublicacion) {
        this.idCollection = idCollection;
        this.nombre = nombre;
        this.autor = autor;
        this.totalVolumenes = totalVolumenes;
        this.totalPoseidos = totalPoseidos;
        this.estadoColeccion = estadoColeccion;
        this.estadoublicacion = estadoublicacion;
    }

    /**
     * Constructor of the Colecciones Class
     * @param nombre - Name of the collection
     * @param autor - Name of the author of the collection
     * @param totalVolumenes - Total amount of volumes of the Collection
     * @param totalPoseidos - Owned Books of this collection
     * @param estadoColeccion - State of the collection, if user keeps track of the collection or has stop gathering that collection
     * @param estadoublicacion - State of the publication of the collection, if they are still publishing it or have stopped
     */
    public Colecciones(String nombre, String autor, int totalVolumenes, int totalPoseidos, String estadoColeccion, String estadoublicacion) {
        this.nombre = nombre;
        this.autor = autor;
        this.totalVolumenes = totalVolumenes;
        this.totalPoseidos = totalPoseidos;
        this.estadoColeccion = estadoColeccion;
        this.estadoublicacion = estadoublicacion;
    }

    /**
     * Gets the id of collection
     * @return - Identifier of the collection
     */
    public int getIdCollection() {
        return idCollection;
    }

    /**
     * Sets the id of the collection
     * @param idCollection - Identifier of the  collection
     */
    public void setIdCollection(int idCollection) {
        this.idCollection = idCollection;
    }

    /**
     * Gets the title of the collection
     * @return - Title of the collection
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the title of the collection
     * @param nombre - Title that you want to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the author of the collection
     * @return - The name of the author
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Sets the name of the author
     * @param autor - Name of the author
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Gets the Total number of volumes that the collection has released
     * @return - Total amount of Volumes
     */
    public int getTotalVolumenes() {
        return totalVolumenes;
    }

    /**
     * Sets the Total number of volumes that the collection has released
     * @param totalVolumenes - Total amount of Volumes
     */
    public void setTotalVolumenes(int totalVolumenes) {
        this.totalVolumenes = totalVolumenes;
    }

    /**
     * Gets the total amount of volumes owned
     * @return - Amount of volumes owned
     */
    public int getTotalPoseidos() {
        return totalPoseidos;
    }

    /**
     * Sets the total amount of volumes owned
     * @param totalPoseidos - Amount of volumes owned
     */
    public void setTotalPoseidos(int totalPoseidos) {
        this.totalPoseidos = totalPoseidos;
    }

    /**
     * Gets the state of the collection regarding the user
     * @return - State of the collection
     */
    public String getEstadoColeccion() {
        return estadoColeccion;
    }

    /**
     * Sets the state of the collection regarding the user
     * @param estadoColeccion - State of the collection ['stopped', 'finished', 'onreading']
     */
    public void setEstadoColeccion(String estadoColeccion) {
        this.estadoColeccion = estadoColeccion;
    }

    /**
     * Gets the state of the collection regarding if it's still being published
     * @return - State of the Collection publication
     */
    public String getEstadoublicacion() {
        return estadoublicacion;
    }

    /**
     * Sets the state of the collection regarding if it's still being published
     * @param estadoublicacion - State of the Collection publication ['cancelado', 'terminado', 'hiatus', 'ongoing']
     */
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
        return this.nombre;
    }
}
