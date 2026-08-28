package config;

import exceptions.DBException;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConfigDB {

    private static final String URL = "jdbc:mariadb://localhost:3306/beakobeta";
    private static final String USR = "root";
    private static final String  PASSWD = "";

    public static Connection openDB() throws DBException {

        try{
            return DriverManager.getConnection(URL,USR,PASSWD);
        } catch (Exception e) {
            throw new DBException("Error: Error durante la conexion a la base de datos");
        }

    }

    public static void closeDB(Connection conn) throws DBException{
        try{
            conn.close();
        } catch (Exception e) {
            throw new DBException("Error:Error durante el cierre de la conexion con la bease de datos");
        }

    }

}
