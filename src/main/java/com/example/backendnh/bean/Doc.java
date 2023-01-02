package com.example.backendnh.bean;
import java.util.Date;

public class Doc {
    private String path;
    private String bh;
    private String wb;
    private String flh;
    private String csrq;
    private String damc;
    private long modified;
    private Date modifiedDate;

    public Doc() {
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getModified() {
        return this.modified;
    }

    public void setModified(long modified) {
        this.modified = modified;
        this.modifiedDate = new Date(this.modified);
    }

    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public String getBh() {
        return this.bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getWb() {
        return this.wb;
    }

    public void setWb(String wb) {
        this.wb = wb;
    }

    public String getFlh() {
        return this.flh;
    }

    public void setFlh(String flh) {
        this.flh = flh;
    }

    public String getCsrq() {
        return this.csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getDamc() {
        return this.damc;
    }

    public void setDamc(String damc) {
        this.damc = damc;
    }
}
