package com.example.backendnh.bean;

import java.util.List;

public class DocModel {
    private static final long serialVersionUID = 1L;
    private int count;
    private int iAllCount = 0;
    private int iCurrPage = 1;
    private int iAllPage = 1;
    private List<Doc> docList;

    public DocModel() {
    }

    public int getiAllCount() {
        return this.iAllCount;
    }

    public void setiAllCount(int iAllCount) {
        this.iAllCount = iAllCount;
    }

    public int getiCurrPage() {
        return this.iCurrPage;
    }

    public void setiCurrPage(int iCurrPage) {
        this.iCurrPage = iCurrPage;
    }

    public int getiAllPage() {
        return this.iAllPage;
    }

    public void setiAllPage(int iAllPage) {
        this.iAllPage = iAllPage;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Doc> getDocList() {
        return this.docList;
    }

    public void setDocList(List<Doc> docList) {
        this.docList = docList;
    }
}
