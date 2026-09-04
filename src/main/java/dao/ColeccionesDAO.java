package dao;

import config.ConfigDB;
import entity.Colecciones;
import exceptions.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ColeccionesDAO {

    /**
     * Adds a new collection to the database
     * @param c - The collection that will be added
     * @throws DBException - Exception that launchs when problems occurs while connecting to the database or when dealing with the PreparedStatement
     */
    public static void insertarColeccion(Colecciones c) throws DBException{

        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "INSERT INTO collections(title, author, totalvolumes, owned, collectionstate, publishingstate) VALUES(?,?,?,?,?,?)";

        try{

          conn = ConfigDB.openDB();
          pS = conn.prepareStatement(sqlStatement);
          pS.setString(1,c.getNombre());
          pS.setString(2,c.getAutor());
          pS.setInt(3,c.getTotalVolumenes());
          pS.setInt(4,c.getTotalPoseidos());
          pS.setString(5,c.getEstadoColeccion());
          pS.setString(6,c.getEstadoublicacion());
          pS.execute();
        } catch (SQLException e) {
            throw new DBException("Message: " + e.getMessage() + "\nCode: " + e.getErrorCode());
        }finally {
            if (conn != null){
                ConfigDB.closeDB(conn);
            }
        }
    }

    /**
     * Returns an ArrayList with every instance of collection inside the Database
     * @return listCollections - ArrayList that contains every collection in the Database
     * @throws DBException - Exception that launchs when problems occurs while connecting to the database or when dealing with the PreparedStatement
     */
    public static ArrayList<Colecciones> listarColecciones() throws DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "SELECT * FROM collections";
        ArrayList<Colecciones> listaColecciones = new ArrayList<Colecciones>();
        ResultSet rS = null;

        try{
            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            rS = pS.executeQuery();

            while(rS.next()){
                listaColecciones.add(new Colecciones(rS.getInt(1),rS.getString(2),rS.getString(3),rS.getInt(4),rS.getInt(5),rS.getString(6),rS.getString(7)));
            }
            return listaColecciones;
        } catch (SQLException e) {
            throw new DBException("Message: " + e.getMessage() + "\nCode: " + e.getErrorCode());
        }finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }
    }

    /**
     * Function that receives the id and the data that the user wants to update and changes it in the DataBase
     * @param nombre - name of the collection
     * @param autor - name of the author of the collection
     * @param totalVolumenes - number of all the volumes released of that collection
     * @param totalPoseidos - number of all the volumes that the user own of the collection
     * @param estadoColeccion - state of the collection regarding if the user still collects it
     * @param estadoublicacion - state of the collection regarding if it is still being published
     * @param id - numeric id of the collection
     * @throws DBException - Exception that launchs when problems occurs while connecting to the database or when dealing with the PreparedStatement
     */
    public static void editarColeccion(String nombre, String autor, int totalVolumenes, int totalPoseidos, String estadoColeccion, String estadoublicacion, int id) throws DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "UPDATE collections SET title = ?, author = ?, totalvolumes = ?, owned = ?, collectionstate = ?, publishingstate = ? WHERE id = ?";

        try {
            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setString(1, nombre);
            pS.setString(2, autor);
            pS.setInt(3, totalVolumenes);
            pS.setInt(4, totalPoseidos);
            pS.setString(5, estadoColeccion);
            pS.setString(6, estadoublicacion);
            pS.setInt(7, id);
            pS.execute();
        }catch (SQLException e) {
            throw new DBException("Message: " + e.getMessage() + "\nCode: " + e.getErrorCode());
        }finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }
    }

    /**
     * Function that deletes a collection from the Database
     * @param id - numeric id of the collectionto delete
     * @throws DBException - Exception that launchs when problems occurs while connecting to the database or when dealing with the PreparedStatement
     */
    public static void eliminarColeccion(int id) throws DBException {
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "DELETE FROM collections WHERE id = ?";

        try{
            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1,id);
            pS.execute();
        } catch (SQLException e) {
            throw new DBException("Message: " + e.getMessage() + "\nCode: " + e.getErrorCode());
        }finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }
    }

    /**
     * Function that gets a collection from its id
     * @param id - Numeric Identifier of the collection
     * @return col - Collection that has the id as its identifier
     * @throws DBException - Exception that launchs when problems occurs while connecting to the database or when dealing with the PreparedStatement
     */
    public static Colecciones obtenerColeccion(int id) throws DBException{
        Connection conn = null;
        PreparedStatement ps = null;
        String sqlStatement = "SELECT * FROM collections WHERE id = ?";
        ResultSet rs = null;

        try {
            conn = ConfigDB.openDB();
            ps = conn.prepareStatement(sqlStatement);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            Colecciones col = null;
            while (rs.next()){
                col = new Colecciones(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7));
            }
            return col;
        } catch (SQLException e) {
            throw new DBException("Message: " + e.getMessage() + "\nCode: " + e.getErrorCode());
        } finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }

    }
}
