#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.common.exception;

import ${package}.client.common.error.ErrorWrapper;

/**
 * Created by ${userName} on ${today}.
 */
public class DatabaseSqlExecuteException extends BaseException {
    public DatabaseSqlExecuteException(ErrorWrapper errorWrapper) {
        super(errorWrapper);
    }

    public DatabaseSqlExecuteException(ErrorWrapper errorWrapper, Throwable cause) {
        super(errorWrapper, cause);
    }

    public DatabaseSqlExecuteException(ErrorWrapper errorWrapper, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(errorWrapper, cause, enableSuppression, writableStackTrace);
    }
}
