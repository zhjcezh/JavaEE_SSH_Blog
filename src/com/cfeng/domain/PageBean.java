package com.cfeng.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter@Setter@ToString
public class PageBean<T> {
    private Integer currentPage;
    private Integer pageSize;
    private Integer index;
    private Integer totalCount;
    private Integer totalPage;
    private List<T> list;
    public void setCurrentPage(Integer currentPage) {
        if (currentPage == null) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }
    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            pageSize = 5;
        }
        this.pageSize = pageSize;
    }
    public Integer getIndex() {
        return (currentPage-1)*pageSize;
    }
    public Integer getTotalPage() {
        double ceil = Math.ceil(totalCount * 1.0 / pageSize);
        return (int)ceil;
    }
}
