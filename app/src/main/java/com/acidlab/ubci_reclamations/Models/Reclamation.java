package com.acidlab.ubci_reclamations.Models;


public class Reclamation {

    private int id;
    private String date;
    private String sujet;
    private int nature_id;
    private int famille_id;
    private int local_id;
    private String rappel;
    private int rappel_nbr;
    private String priority_id;
    private String content;
    private String comment;
    private int entity_id;
    private String interlocuteur;
    private int status_id;
    private int user_id;

    public Reclamation() {
    }

    public Reclamation(int id, String date, String sujet, int nature_id, int famille_id, int local_id, String rappel, int rappel_nbr, String priority_id, String content, String comment, int entity_id, String interlocuteur, int status_id, int user_id) {
        this.id = id;
        this.date = date;
        this.sujet = sujet;
        this.nature_id = nature_id;
        this.famille_id = famille_id;
        this.local_id = local_id;
        this.rappel = rappel;
        this.rappel_nbr = rappel_nbr;
        this.priority_id = priority_id;
        this.content = content;
        this.comment = comment;
        this.entity_id = entity_id;
        this.interlocuteur = interlocuteur;
        this.status_id = status_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public int getNature_id() {
        return nature_id;
    }

    public void setNature_id(int nature_id) {
        this.nature_id = nature_id;
    }

    public int getFamille_id() {
        return famille_id;
    }

    public void setFamille_id(int famille_id) {
        this.famille_id = famille_id;
    }

    public int getLocal_id() {
        return local_id;
    }

    public void setLocal_id(int local_id) {
        this.local_id = local_id;
    }

    public String getRappel() {
        return rappel;
    }

    public void setRappel(String rappel) {
        this.rappel = rappel;
    }

    public int getRappel_nbr() {
        return rappel_nbr;
    }

    public void setRappel_nbr(int rappel_nbr) {
        this.rappel_nbr = rappel_nbr;
    }

    public String getPriority_id() {
        return priority_id;
    }

    public void setPriority_id(String priority_id) {
        this.priority_id = priority_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    public String getInterlocuteur() {
        return interlocuteur;
    }

    public void setInterlocuteur(String interlocuteur) {
        this.interlocuteur = interlocuteur;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", sujet='" + sujet + '\'' +
                ", nature_id=" + nature_id +
                ", famille_id=" + famille_id +
                ", local_id=" + local_id +
                ", rappel='" + rappel + '\'' +
                ", rappel_nbr=" + rappel_nbr +
                ", priority_id='" + priority_id + '\'' +
                ", content='" + content + '\'' +
                ", comment='" + comment + '\'' +
                ", entity_id=" + entity_id +
                ", interlocuteur='" + interlocuteur + '\'' +
                ", status_id=" + status_id +
                ", user_id=" + user_id +
                '}';
    }
}