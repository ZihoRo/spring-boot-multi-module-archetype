#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.common.list;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by ${userName} on ${today}.
 */
@Data
public class ListWrapper<Value> {
    private List<Value> rows;
    private long count;

    public ListWrapper() {
        this(Collections.<Value>emptyList(), 0);
    }

    public ListWrapper(List<Value> rows) {
        this(rows, rows == null ? 0L : (long) rows.size());
    }

    public ListWrapper(List<Value> rows, int count) {
        this(rows, (long) count);
    }

    public ListWrapper(List<Value> rows, long count) {
        this.rows = (rows == null || rows.isEmpty()) ? (List<Value>) Collections.emptyList() : rows;
        this.count = count;
    }

    public <T> ListWrapper<T> convert(Function<Value, T> function) {
        if (rows == null || rows.isEmpty()) {
            rows = Collections.emptyList();
        }
        if (rows == Collections.emptyList()) {
            return (ListWrapper<T>) this;
        }
        return new ListWrapper<T>((List<T>) rows.stream().map(function).collect(Collectors.toList()), count);
    }
}
