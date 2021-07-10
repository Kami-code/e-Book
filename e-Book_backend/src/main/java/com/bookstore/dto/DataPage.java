package com.bookstore.dto;

import com.bookstore.entity.Book;
import com.bookstore.entity.User;

import java.util.List;

public class DataPage<T> {
    private List<T> data;    //数据库查询出的数据
    private int totalRecord;    // 总记录数，数据库查询
    private int pageNumber;     // 当前页码
    private int pageSize = 5;       // 每页的记录数量

//    private int index;          //当前索引，计算得到
//    priv

    public DataPage(int totalRecord, int pageNumber) {
        this.totalRecord = totalRecord;
        this.pageNumber = pageNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }
    public int getPageSize() {
        return pageSize;
    }
    public int getTotalRecord() {
        return totalRecord;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
    public int getIndex() {
        /* 计算得到的当前索引值 */
        return (getPageNumber() - 1) * pageSize;
    }
    public int getTotalPage() {
        /* 总页数 */
        if (totalRecord % pageSize == 0) {
            return totalRecord / pageSize;
        }
        else {
            return (totalRecord / pageSize) + 1;
        }
    }
}
