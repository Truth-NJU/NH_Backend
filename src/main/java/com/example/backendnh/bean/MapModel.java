package com.example.backendnh.bean;

public class MapModel {
    private static final long serialVersionUID = -1129725639111648194L;
    private String key;
    private String value;

    public MapModel() {
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
