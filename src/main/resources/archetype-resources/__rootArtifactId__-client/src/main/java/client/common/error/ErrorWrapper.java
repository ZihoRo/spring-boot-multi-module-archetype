#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.common.error;

import lombok.Data;

/**
 * Created by ${userName} on ${today}.
 */
@Data
public class ErrorWrapper {
    private long code;
    private String name;
    private String msg;

    public ErrorWrapper() {
        this(200, "EMPTY", "EMPTY");
    }

    public ErrorWrapper(long code, String name, String msg) {
        this.code = code;
        this.name = (name == null || name.trim().equals("")) ? "EMPTY" : name;
        this.msg = (msg == null || msg.trim().equals("")) ? "EMPTY" : msg;
    }
}
