package com.qianfeng.page;

public class Page {
    private Integer pageNo;//当前页
    private Integer pageSize;//页容量
    private Boolean hasNext;//是否有下一页
    private Boolean hasPre;//是否有上一页
    private Long count;//数据库表总记录数
    private Integer pageCount; //总页数
    private Object data; //当前页数据

    public Page() {
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Boolean getHasPre() {
        return hasPre;
    }

    public void setHasPre(Boolean hasPre) {
        this.hasPre = hasPre;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", hasNext=" + hasNext +
                ", hasPre=" + hasPre +
                ", count=" + count +
                ", pageCount=" + pageCount +
                ", data=" + data +
                '}';
    }
}
