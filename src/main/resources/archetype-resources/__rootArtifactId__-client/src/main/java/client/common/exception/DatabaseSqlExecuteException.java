#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.common.exception;

/**
 * Created by ${userName} on ${today}.
 */
public class DatabaseSqlExecuteException extends RuntimeException {
    public DatabaseSqlExecuteException() {
    }

    public DatabaseSqlExecuteException(String message) {
        super(message);
    }

    public DatabaseSqlExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseSqlExecuteException(Throwable cause) {
        super(cause);
    }
}
