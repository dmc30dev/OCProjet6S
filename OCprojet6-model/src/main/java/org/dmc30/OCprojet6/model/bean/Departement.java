package org.dmc30.OCprojet6.model.bean;

public class Departement {

    private int code;
    private String nom;
    private Region region;


    public Departement() {
    }

    public Departement(int code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
