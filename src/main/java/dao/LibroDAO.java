package dao;

import config.ConfigDB;
import entity.Libro;
import exceptions.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibroDAO {
    /**
     *
     * @param l
     * @throws DBException
     */
    public static void insertarLibro(Libro l) throws DBException{

        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "INSERT INTO libros(numeroVolumen, editorial, lenguage, estadoLibro, coleccion) VALUES(?,?,?,?,?)";


        try{

            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1,l.getNumeroVolumen());
            pS.setString(2,l.getEditorial());
            pS.setString(3,l.getLenguaje());
            pS.setString(4,l.getEstadoLibro());
            pS.setInt(5,l.getColeccion());
            pS.execute();


        } catch (Exception e) {
            throw new DBException("Error: Error durante la insercion de libro en la coleccion");
        } finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }

    }

    /**
     *
     * @param id
     * @return
     * @throws DBException
     */
    public static Libro buscarLibro(int id) throws  DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        ResultSet rS =  null;
        Libro l = null;
        String sqlStatement = "SELECT * FROM libros WHERE id = ?";

        try{
            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1,id);
            rS = pS.executeQuery();
            while(rS.next()){
                l = new Libro(rS.getInt(2),rS.getString(3),rS.getString(4),rS.getString(5),rS.getInt(6));
            }
            return l;
        } catch (Exception e) {
            throw new DBException("Error: Error durante la busqueda del libro ");
        }finally {
            if (conn != null) {
                ConfigDB.closeDB(conn);
            }
        }
    }

    /**
     * 
     * @return
     * @throws DBException
     */
    public static ArrayList<Libro> listarLibros() throws  DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        ResultSet rS =  null;
        ArrayList<Libro> listaLibros = new ArrayList<Libro>();
        String sqlStatement = "SELECT *  FROM libros";

        try{
            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            rS = pS.executeQuery();
            while(rS.next()){
               listaLibros.add(new Libro(rS.getInt(2),rS.getString(3),rS.getString(4),rS.getString(5),rS.getInt(6)));
            }
            return listaLibros;
        } catch (Exception e) {
            throw new DBException("Error: Error al copilar libros");
        }finally {
            if (conn != null) {
                ConfigDB.closeDB(conn);
            }
        }

    }

    public static void editarLibros(int id, int numeroVolumen,String editorial,String lenguage, String estadoLibro, int coleccion) throws  DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "UPDATE libros SET numerovolumen = ?, editorial = ?, lenguage = ?, estadolibro = ?, coleccion = ? WHERE id = ?";

        try{
            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1,numeroVolumen);
            pS.setString(2,editorial);
            pS.setString(3,lenguage);
            pS.setString(4,estadoLibro);
            pS.setInt(5,coleccion);
            pS.setInt(6,id);
            pS.execute();
        } catch (Exception e) {
            throw new DBException("Error: Error durante la modificacion del libro ");
        }finally {
            if (conn != null) {
                ConfigDB.closeDB(conn);
            }
        }

    }

    public static void eliminarLibro(int id) throws DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "DELETE FROM libros WHERE id = ?";

                try{
                    conn = ConfigDB.openDB();
                    pS = conn.prepareStatement(sqlStatement);
                    pS.setInt(1,id);
                    pS.execute();
                } catch (Exception e) {
                    throw new DBException("Error al conectarse alabase de datos");
                }finally {
                    if(conn != null){
                        ConfigDB.closeDB(conn);
                    }
                }

    }

    public static int searchBookByVolume(int numVolume, int collection) throws DBException{
        Connection conn = null;
        PreparedStatement ps = null;
        String sqlStatement = "SELECT id FROM libros WHERE numerovolumen = ? AND coleccion = ?";
        ResultSet rs = null;

        try{
            conn = ConfigDB.openDB();
            ps = conn.prepareStatement(sqlStatement);
            ps.setInt(1,numVolume);
            ps.setInt(2,collection);
            rs = ps.executeQuery();
            int bookId = -1;
            while(rs.next()){
                bookId = rs.getInt("id");
            }
            return bookId;
        }catch (DBException | SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }
    }

    public static ArrayList<Libro> listBooksByCollection(int collectionId) throws DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "SELECT * FROM libros WHERE coleccion = ?";
        ResultSet rS = null;
        ArrayList<Libro> booksOfCollection = new ArrayList<Libro>();

        try{
            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1,collectionId);
            rS = pS.executeQuery();
            while(rS.next()){
                booksOfCollection.add(new Libro(rS.getInt(2),rS.getString(3),rS.getString(4),rS.getString(5),rS.getInt(6)));
            }
            return booksOfCollection;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }
    }

}
