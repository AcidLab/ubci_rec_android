package com.acidlab.ubci_reclamations.Models;


import java.util.ArrayList;
import java.util.List;

public class Local {

    private int id;

    private String code;

    private String label;

    private String abrege;

    private String region;

    static private List<Local> Locals = new ArrayList<Local>();


    public Local(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public static List<Local> getLocals() {
        return Locals;
    }

    public static void setLocals(List<Local> locals) {
        Locals = locals;
    }

    // Getter Methods

    public int getId() {
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

    public void setId(int id) {
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
