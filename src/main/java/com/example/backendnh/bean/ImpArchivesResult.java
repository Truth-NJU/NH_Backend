package com.example.backendnh.bean;

import java.util.ArrayList;
import java.util.List;

public class ImpArchivesResult {
    public boolean blnImp = false;
    public List<String> alSucessArchive = new ArrayList<>();
    public List<String> alErrorArchive = new ArrayList<>();
    public List<String> alExistArchive = new ArrayList<>();

    public ImpArchivesResult() {
    }

    public boolean isBlnImp() {
        return this.blnImp;
    }

    public void setBlnImp(boolean blnImp) {
        this.blnImp = blnImp;
    }

    public List<String> getAlSucessArchive() {
        return this.alSucessArchive;
    }

    public void setAlSucessArchive(List<String> alSucessArchive) {
        this.alSucessArchive = alSucessArchive;
    }

    public List<String> getAlErrorArchive() {
        return this.alErrorArchive;
    }

    public void setAlErrorArchive(List<String> alErrorArchive) {
        this.alErrorArchive = alErrorArchive;
    }

    public List<String> getAlExistArchive() {
        return this.alExistArchive;
    }

    public void setAlExistArchive(List<String> alExistArchive) {
        this.alExistArchive = alExistArchive;
    }
}
