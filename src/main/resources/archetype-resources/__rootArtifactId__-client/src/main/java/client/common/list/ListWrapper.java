#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.common.list;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by ${userName} on ${today}.
 */
@EqualsAndHashCode
@ToString
public class ListWrapper<Value> {
    private List<Value> rows;
    private Long total;
    private Long currentPage;
    private Long pageSize;

    public ListWrapper() {
        this(Collections.emptyList());
    }

    public ListWrapper(List<Value> rows) {
        this(rows, null, (Long) null, (Long) null);
    }

    public ListWrapper(List<Value> rows, Integer total, Integer currentPage, Integer pageSize) {
        this(rows, total == null ? null : total.longValue(), currentPage == null ? null : currentPage.longValue(), pageSize == null ? null : pageSize.longValue());
    }

    public ListWrapper(List<Value> rows, Long total, Integer currentPage, Integer pageSize) {
        this(rows, total == null ? null : total.longValue(), currentPage == null ? null : currentPage.longValue(), pageSize == null ? null : pageSize.longValue());
    }

    public ListWrapper(List<Value> rows, Long total, Long currentPage, Integer pageSize) {
        this(rows, total == null ? null : total.longValue(), currentPage == null ? null : currentPage.longValue(), pageSize == null ? null : pageSize.longValue());
    }

    public ListWrapper(List<Value> rows, Long total, Integer currentPage, Long pageSize) {
        this(rows, total == null ? null : total.longValue(), currentPage == null ? null : currentPage.longValue(), pageSize == null ? null : pageSize.longValue());
    }

    public ListWrapper(List<Value> rows, Long total, Long currentPage, Long pageSize) {
        setRows(rows);
        setTotal(total);
        setCurrentPage(currentPage);
        setPageSize(pageSize);
    }

    public List<Value> getRows() {
        return (rows == null || rows.isEmpty()) ? Collections.emptyList() : rows;
    }

    private void setRows(List<Value> rows) {
        this.rows = (rows == null || rows.isEmpty()) ? Collections.emptyList() : rows;
    }

    public Long getTotal() {
        return total == null ? getRows().size() : total;
    }

    private void setTotal(Long total) {
        this.total = total == null ? getRows().size() : total.longValue();
    }

    public Long getCurrentPage() {
        return currentPage == null ? 1L : currentPage;
    }

    private void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage == null ? 1 : currentPage.longValue();
    }

    public Long getPageSize() {
        return pageSize == null ? getRows().size() : pageSize;
    }

    private void setPageSize(Long pageSize) {
        this.pageSize = pageSize == null ? getRows().size() : pageSize.longValue();
    }

    public <T> ListWrapper<T> convert(Function<Value, T> function) {
        if (getRows().isEmpty()) {
            rows = getRows();
        }
        if (rows == Collections.emptyList()) {
            return (ListWrapper<T>) this;
        }
        return new ListWrapper(getRows().stream().map(function).collect(Collectors.toList()), getTotal(), getCurrentPage(), getPageSize());
    }
}
