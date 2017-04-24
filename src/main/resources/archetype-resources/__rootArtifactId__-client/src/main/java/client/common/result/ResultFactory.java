#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.common.result;

import ${package}.client.common.error.ErrorWrapper;
import ${package}.client.common.list.ListWrapper;

import java.util.Collections;
import java.util.List;

/**
 * Created by ${userName} on ${today}.
 */
public class ResultFactory {
    private static final Result<?> emptySuccess = new Result<Object>(true);
    private static final Result<?> emptyListSuccess = new Result<Object>(true, new ListWrapper<Object>(Collections.emptyList(), 0));

    public static <Value> Result<Value> success() {
        return (Result<Value>) emptySuccess;
    }

    public static <Value> Result<Value> success(Value data) {
        return success(data, null);
    }

    public static <Value> Result<Value> success(Value data, ErrorWrapper error) {
        return new Result<Value>(true, data, error);
    }

    public static <Value> Result<Value> success(ErrorWrapper error) {
        return success(null, error);
    }

    public static <Value> Result<Value> successCheck(Value data, ErrorWrapper error) {
        return new Result<Value>(true, data, data == null ? error : null);
    }

    public static <Value> Result<ListWrapper<Value>> successList() {
        return (Result<ListWrapper<Value>>) emptyListSuccess;
    }

    public static <Value> Result<ListWrapper<Value>> successList(List<Value> data) {
        if (data == null || data.isEmpty()) {
            return successList();
        }
        return successList(data, (long) data.size());
    }

    public static <Value> Result<ListWrapper<Value>> successList(List<Value> data, int count) {
        return successList(data, (long) count);
    }

    public static <Value> Result<ListWrapper<Value>> successList(List<Value> data, long count) {
        if (data == null || data.isEmpty()) {
            if (count == 0L) {
                return successList();
            }
            data = Collections.emptyList();
        }
        return new Result<ListWrapper<Value>>(true, new ListWrapper<Value>(data, count));
    }

    public static <Value> Result<Value> error(int code, String name, String msg) {
        return error((long) code, name, msg);
    }

    public static <Value> Result<Value> error(int code, String msg) {
        return error((long) code, msg, msg);
    }

    public static <Value> Result<Value> error(long code, String msg) {
        return error(code, msg, msg);
    }

    public static <Value> Result<Value> error(long code, String name, String msg) {
        return new Result<Value>(false, null, new ErrorWrapper(code, name, msg));
    }

    public static <Value> Result<Value> error(Result<?> result) {
        return new Result<Value>(false, null, result.getError());
    }

}
