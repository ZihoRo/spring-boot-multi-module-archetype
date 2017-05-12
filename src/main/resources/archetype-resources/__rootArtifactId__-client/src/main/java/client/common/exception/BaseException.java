#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.common.exception;

import ${package}.client.common.error.ErrorWrapper;
import lombok.Getter;

/**
 * Created by ${userName} on ${today}.
 */
@Getter
public abstract class BaseException extends RuntimeException {
    private ErrorWrapper errorWrapper;

    public BaseException(ErrorWrapper errorWrapper) {
        super(toJson(errorWrapper));
        this.errorWrapper = errorWrapper;
    }

    public BaseException(ErrorWrapper errorWrapper, Throwable cause) {
        super(toJson(errorWrapper), cause);
        this.errorWrapper = errorWrapper;
    }

    public BaseException(ErrorWrapper errorWrapper, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(toJson(errorWrapper), cause, enableSuppression, writableStackTrace);
        this.errorWrapper = errorWrapper;
    }

    private static String toJson(ErrorWrapper errorWrapper) {
        return errorWrapper == null ? null : errorWrapper.toJson();
    }
}
