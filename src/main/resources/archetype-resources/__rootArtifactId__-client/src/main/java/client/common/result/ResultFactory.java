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
    private static final Result<?> emptySuccess = new Result<>(true);
    private static final Result<?> emptyListSuccess = new Result<>(true, new ListWrapper<>(Collections.emptyList()));

    public static <Value> Result<Value> success() {
        return success(null, null);
    }

    public static <Value> Result<Value> success(Value data) {
        return success(data, null);
    }

    public static <Value> Result<Value> success(ErrorWrapper error) {
        return success(null, error);
    }

    public static <Value> Result<Value> successCheck(Value data, ErrorWrapper error) {
        return new Result<>(true, data, data == null ? error : null);
    }

    public static <Value> Result<Value> success(Value data, ErrorWrapper error) {
        if (data == null && error == null) {
            return (Result<Value>) emptySuccess;
        }
        return new Result<>(true, data, error);
    }

    public static <Value> Result<ListWrapper<Value>> successList() {
        return successList(null, null, (Long) null, (Long) null);
    }

    public static <Value> Result<ListWrapper<Value>> successList(List<Value> data) {
        return successList(data, null, (Long) null, (Long) null);
    }

    public static <Value> Result<ListWrapper<Value>> successList(List<Value> data, Integer total, Integer currentPage, Integer pageSize) {
        return successList(data, total == null ? null : total.longValue(), currentPage == null ? null : currentPage.longValue(), pageSize == null ? null : pageSize.longValue());
    }

    public static <Value> Result<ListWrapper<Value>> successList(List<Value> data, Long total, Integer currentPage, Integer pageSize) {
        return successList(data, total == null ? null : total.longValue(), currentPage == null ? null : currentPage.longValue(), pageSize == null ? null : pageSize.longValue());
    }

    public static <Value> Result<ListWrapper<Value>> successList(List<Value> data, Long total, Long currentPage, Integer pageSize) {
        return successList(data, total == null ? null : total.longValue(), currentPage == null ? null : currentPage.longValue(), pageSize == null ? null : pageSize.longValue());
    }

    public static <Value> Result<ListWrapper<Value>> successList(List<Value> data, Long total, Integer currentPage, Long pageSize) {
        return successList(data, total == null ? null : total.longValue(), currentPage == null ? null : currentPage.longValue(), pageSize == null ? null : pageSize.longValue());
    }

    public static <Value> Result<ListWrapper<Value>> successList(List<Value> data, Long total, Long currentPage, Long pageSize) {
        if (data == null || data.isEmpty()) {
            if (total == null || total.longValue() == 0L) {
                return (Result<ListWrapper<Value>>) emptyListSuccess;
            }
            data = Collections.emptyList();
        }
        return new Result<>(true, new ListWrapper<>(data, total, currentPage, pageSize));
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
        return new Result<>(false, null, new ErrorWrapper(code, name, msg));
    }

    public static <Value> Result<Value> error(Result<?> result) {
        return new Result<>(false, null, result.getError());
    }

}
