package com.acidlab.ubci_reclamations.Models;


public class Entity {

    private float id;

    private String code;

    private String label;

    private String abrege;

    private String region;



    public Entity(float id, String code, String label, String abrege, String region) {
        this.id = id;
        this.code = code;
        this.label = label;
        this.abrege = abrege;
        this.region = region;
    }
// Getter Methods


    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAbrege() {
        return abrege;
    }

    public void setAbrege(String abrege) {
        this.abrege = abrege;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Entity() {
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", label='" + label + '\'' +
                ", abrege='" + abrege + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}