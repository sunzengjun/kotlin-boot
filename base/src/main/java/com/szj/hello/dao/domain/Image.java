package com.szj.hello.dao.domain;

import java.util.Date;

/**
 * Created by Sun on 2017/3/31.
 */
public class Image {
    private long id;
    private long bookId;
    private String url;
    private Date ctime;
    private Date mtime;
    private int isDelete;

    public Image() {
    }

    public Image(long bookId, String url) {
        this.bookId = bookId;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long refundId) {
        this.bookId = refundId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
