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

    public static void insertarColeccion(Colecciones c) throws DBException{

        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "INSERT INTO colecciones(nombre, autor, totalvolumenes, totalposeidos, estadocoleccion, estadopublicacion) VALUES(?,?,?,?,?,?)";

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
            throw new DBException("Error al insertar Coleccion");
        }finally {
            if (conn != null){
                ConfigDB.closeDB(conn);
            }
        }
    }

    public static ArrayList<Colecciones> listarColecciones() throws DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "SELECT * FROM colecciones";
        ArrayList<Colecciones> listaColecciones = new ArrayList<Colecciones>();
        ResultSet rS = null;

        try{
            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            rS = pS.executeQuery();

            while(rS.next()){
                listaColecciones.add(new Colecciones(rS.getString(2),rS.getString(3),rS.getInt(4),rS.getInt(5),rS.getString(6),rS.getString(7)));
            }
            return listaColecciones;
        } catch (Exception e) {
            throw new DBException("Error al conectarse con la base de datos");
        }finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }
    }

    public static ArrayList<String> listarNombreColecciones() throws DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "SELECT * FROM colecciones";
        ArrayList<String> listaColecciones = new ArrayList<String>();
        ResultSet rS = null;

        try{
            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            rS = pS.executeQuery();

            while(rS.next()){
                listaColecciones.add(rS.getString(2));
            }
            return listaColecciones;
        } catch (Exception e) {
            throw new DBException("Error al conectarse con labase de datos");
        }finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }
    }

    public static void editarColeccion(String nombre, String autor, int totalVolumenes, int totalPoseidos, String estadoColeccion, String estadoublicacion, int id) throws DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "UPDATE colecciones SET nombre = ?, autor = ?, totalvolumenes = ?, totalposeidos = ?, estadocoleccion = ?, estadopublicacion = ? WHERE id = ?";

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
            throw new DBException("Error al ejecutar comando de sql");
        }finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }
    }

    public static void eliminarColeccion(int id) throws DBException {
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "DELETE FROM colecciones WHERE id = ?";

        try{
            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1,id);
            pS.execute();
        } catch (SQLException e) {
            throw new DBException("Error alejecutar orden sql");
        }finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }
    }

    public static int obtenerId(String nombre) throws DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "SELECT id FROM colecciones WHERE nombre = ?";
        ResultSet rS = null;
        try{
            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setString(1,nombre);
            rS = pS.executeQuery();
            while(rS.next()){
                return rS.getInt(1);
            }
        } catch (SQLException e) {
            throw new DBException("Error al ejecutar orden sql");
        }

        return 0;
    }

    public static Colecciones obtenerColeccion(int id) throws DBException{
        Connection conn = null;
        PreparedStatement ps = null;
        String sqlStatement = "SELECT * FROM colecciones WHERE id = ?";
        ResultSet rs = null;

        try {
            conn = ConfigDB.openDB();
            ps = conn.prepareStatement(sqlStatement);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            Colecciones col = null;
            while (rs.next()){
                col = new Colecciones(rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7));
            }
            return col;
        } catch (DBException | SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }

    }

    public static void incrementOwnedBooks(int id) throws DBException{
        Connection conn = null;
        PreparedStatement pS = null;
        String sqlStatement = "UPDATE colecciones SET totalposeidos = totalposeidos + 1 WHERE id = ?";
        try{
            conn = ConfigDB.openDB();
            pS = conn.prepareStatement(sqlStatement);
            pS.setInt(1,id);
            pS.execute();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }finally {
            if(conn != null){
                ConfigDB.closeDB(conn);
            }
        }
    }
}
