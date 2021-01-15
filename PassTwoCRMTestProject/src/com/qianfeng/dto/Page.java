package com.qianfeng.dto;

public class Page {
    private int pageNo;
    private int pageSize;
    private int pageCount;
    private int count;
    private boolean hasPer;
    private boolean hasNext;
    private Object pageMessage;

    public Page() {
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isHasPer() {
        return hasPer;
    }

    public void setHasPer(boolean hasPer) {
        this.hasPer = hasPer;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Object getPageMessage() {
        return pageMessage;
    }

    public void setPageMessage(Object pageMessage) {
        this.pageMessage = pageMessage;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", pageCount=" + pageCount +
                ", count=" + count +
                ", hasPer=" + hasPer +
                ", hasNext=" + hasNext +
                ", pageMessage=" + pageMessage +
                '}';
    }
}
