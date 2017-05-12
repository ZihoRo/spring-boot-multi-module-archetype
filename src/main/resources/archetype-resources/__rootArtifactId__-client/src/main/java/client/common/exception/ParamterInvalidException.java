#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.common.exception;

import ${package}.client.common.error.ErrorWrapper;

/**
 * Created by ${userName} on ${today}.
 */
public class ParamterInvalidException extends BaseException {
    public ParamterInvalidException(ErrorWrapper errorWrapper) {
        super(errorWrapper);
    }

    public ParamterInvalidException(ErrorWrapper errorWrapper, Throwable cause) {
        super(errorWrapper, cause);
    }

    public ParamterInvalidException(ErrorWrapper errorWrapper, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(errorWrapper, cause, enableSuppression, writableStackTrace);
    }
}
