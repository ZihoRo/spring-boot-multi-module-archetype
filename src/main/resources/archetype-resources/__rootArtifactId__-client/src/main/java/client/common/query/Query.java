#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.common.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${userName} on ${today}.
 */
public class Query {
    @Getter
    @Setter
    private boolean pageEnable = true;
    @Getter
    @Setter
    private boolean orderByEnable = false;
    private Long pageSize;
    private Long currentPage;
    private Long totalCount;
    @Getter
    private List<OrderBy> orderByList;
    private Long startRow;
    private Long endRow;

    public void putOrderBy(String column, Boolean asc) {
        if (column == null || column.trim().equals("") || asc == null) {
            return;
        }
        if (orderByList == null) {
            orderByList = new ArrayList<OrderBy>();
        }
        orderByList.add(new OrderBy(column, asc ? "asc" : "desc"));
    }

    public void putTotalCount(int totalCount) {
        putTotalCount(Long.valueOf(totalCount));
    }

    public void putTotalCount(long totalCount) {
        putTotalCount(Long.valueOf(totalCount));
    }

    public void putTotalCount(Integer totalCount) {
        if (totalCount != null)
            putTotalCount(Long.valueOf(totalCount));
    }

    public void putTotalCount(Long totalCount) {
        this.totalCount = totalCount;
        if (pageEnable) {
            initPage();
        }
    }

    public Long getPageSize() {
        if (pageEnable) {
            initPage();
            return pageSize;
        }
        return null;
    }

    public void setPageSize(int pageSize) {
        setPageSize(Long.valueOf(pageSize));
    }

    public void setPageSize(long pageSize) {
        setPageSize(Long.valueOf(pageSize));
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != null)
            setPageSize(Long.valueOf(pageSize));
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
        if (pageEnable) {
            initPage();
        }
    }

    public Long getCurrentPage() {
        if (pageEnable) {
            initPage();
            return currentPage;
        }
        return null;
    }

    public void setCurrentPage(int currentPage) {
        setCurrentPage(Long.valueOf(currentPage));
    }

    public void setCurrentPage(long currentPage) {
        setCurrentPage(Long.valueOf(currentPage));
    }

    public void setCurrentPage(Integer currentPage) {
        if (currentPage != null)
            setCurrentPage(Long.valueOf(currentPage));
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
        if (pageEnable) {
            initPage();
        }
    }

    public Long getStartRow() {
        if (pageEnable) {
            initPage();
            return startRow;
        }
        return null;
    }

    public Long getEndRow() {
        if (pageEnable) {
            initPage();
            return endRow;
        }
        return null;
    }

    private void initPage() {
        if (pageSize == null || pageSize.longValue() < 1L) {
            pageSize = Long.valueOf(20);
        }
        if (currentPage == null || currentPage.longValue() < 1L) {
            currentPage = Long.valueOf(1);
        }
        if (totalCount == null || totalCount.longValue() < 1L) {
            totalCount = Long.MAX_VALUE;
        }
        long totalPage = totalCount.longValue() / pageSize.longValue();
        if (totalPage * pageSize.longValue() < totalCount.longValue()) {
            totalPage = totalPage + 1;
        }
        if (currentPage.longValue() > totalPage) {
            currentPage = Long.valueOf(totalPage);
        }
        startRow = Long.valueOf(pageSize.longValue() * (currentPage.longValue() - 1L));
        endRow = Long.valueOf(startRow.longValue() + pageSize.longValue());
    }

    @Data
    @AllArgsConstructor
    class OrderBy {
        private String column;
        private String sort;
    }
}
