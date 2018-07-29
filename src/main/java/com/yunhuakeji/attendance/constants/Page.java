package com.yunhuakeji.attendance.constants;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

    private int pageNo = 1;                // 当前页数
    private int pageSize = 10;            // 每页显示行数
    private int totalCount;                // 总行数
    private int totalPages;                // 总页数

    private List<T> result = new ArrayList<T>();// 查询结果

    public Page() {
        super();

    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo <= 0 ? 1 : pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {

        this.totalCount = totalCount;

        if (totalCount < 0) {

            this.totalPages = -1;
        }

        int pages = totalCount / this.pageSize;

        this.totalPages = totalCount % this.pageSize > 0 ? pages + 1 : pages;

    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

}
