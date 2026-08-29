package exceptions;

/**
 * Exception Class to manage the excception related to the Database
 */
public class DBException extends Exception {
    /**
     * Constructor of the exception
     * @param message - Message of the Exception
     */
    public DBException(String message) {
        super(message);
    }
}
