package com.acidlab.ubci_reclamations.Models;


public class Local {

    private float id;

    private String code;

    private String label;

    private String abrege;

    private String region;


    public Local(float id, String label) {
        this.id = id;
        this.label = label;
    }

    // Getter Methods

    public float getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public String getAbrege() {
        return abrege;
    }

    public String getRegion() {
        return region;
    }


    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setAbrege(String abrege) {
        this.abrege = abrege;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Local() {
    }

    @Override
    public String toString() {
        return "Local{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", label='" + label + '\'' +
                ", abrege='" + abrege + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
