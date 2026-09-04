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
     * Adds new book to the Database
     * @param l - Book that will be added
     * @throws DBException - Exception that launchs when problems occurs while connecting to the database or when dealing with the PreparedStatement
     */
    public static void insertarLibro(Libro l) throws DBException{

        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "INSERT INTO books(volumenumber, editorial, language, bookstate, collection) VALUES(?,?,?,?,?)";


        try{

            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1,l.getNumeroVolumen());
            pS.setString(2,l.getEditorial());
            pS.setString(3,l.getLenguaje());
            pS.setString(4,l.getEstadoLibro());
            pS.setInt(5,l.getColeccion());
            pS.execute();


        } catch (SQLException e) {
            throw new DBException("Message: " + e.getMessage() + "\nCode: " + e.getErrorCode());
        } finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }

    }

    /**
     * Search for a book with the specified id
     * @param id - Identifier of the book
     * @return l - Book with the specified id
     * @throws DBException - Exception that launchs when problems occurs while connecting to the database or when dealing with the PreparedStatement
     */
    public static Libro buscarLibro(int id) throws  DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        ResultSet rS =  null;
        Libro l = null;
        String sqlStatement = "SELECT * FROM books WHERE id = ?";

        try{
            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1,id);
            rS = pS.executeQuery();
            while(rS.next()){
                l = new Libro(rS.getInt(1), rS.getInt(2),rS.getString(3),rS.getString(4),rS.getString(5),rS.getInt(6));
            }
            return l;
        } catch (SQLException e) {
            throw new DBException("Message: " + e.getMessage() + "\nCode: " + e.getErrorCode());
        }finally {
            if (conn != null) {
                ConfigDB.closeDB(conn);
            }
        }
    }

    /**
     * Lists all that book that are from the same Id
     * @param collectionId - Id of the collection from which the book iis from
     * @return booksOfCollection - List of all of the book of the collection
     * @throws DBException - Exception that launchs when problems occurs while connecting to the database or when dealing with the PreparedStatement
     */
    public static ArrayList<Libro> listBooksByCollection(int collectionId) throws DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "SELECT * FROM books WHERE collection = ?";
        ResultSet rS = null;
        ArrayList<Libro> booksOfCollection = new ArrayList<Libro>();

        try{
            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1,collectionId);
            rS = pS.executeQuery();
            while(rS.next()){
                booksOfCollection.add(new Libro(rS.getInt(1), rS.getInt(2),rS.getString(3),rS.getString(4),rS.getString(5),rS.getInt(6)));
            }
            return booksOfCollection;
        } catch (SQLException e) {
            throw new DBException("Message: " + e.getMessage() + "\nCode: " + e.getErrorCode());
        }finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }
    }

    /**
     * Edit the data of the books that have the id passed as a parameter
     * @param id - Identifier of the book
     * @param numeroVolumen - Number of the volume in that collection
     * @param editorial - Editorial that publishes the book
     * @param lenguage - Language in which the book is written only accepts three letters max (ENG-PT-ESP-JP)
     * @param estadoLibro - Reading state of the book in the collection so if the user starts multiple books at the same time it can keep track on which are finished, started, or still hasnt started
     * @param coleccion - Identifier of the collection from which the book is from
     * @throws DBException - Exception that launchs when problems occurs while connecting to the database or when dealing with the PreparedStatement
     */
    public static void editarLibros(int id, int numeroVolumen, String editorial, String lenguage, String estadoLibro, int coleccion) throws  DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "UPDATE books SET volumenumber = ?, editorial = ?, language = ?, bookstate = ?, collection = ? WHERE id = ?";

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
        } catch (SQLException e) {
            throw new DBException("Message: " + e.getMessage() + "\nCode: " + e.getErrorCode());
        }finally {
            if (conn != null) {
                ConfigDB.closeDB(conn);
            }
        }

    }

    /**
     * Deletes the book that has the identifier
     * @param id - Identifier of the book
     * @throws DBException - Exception that launchs when problems occurs while connecting to the database or when dealing with the PreparedStatement
     */
    public static void eliminarLibro(int id) throws DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "DELETE FROM books WHERE id = ?";

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
}
