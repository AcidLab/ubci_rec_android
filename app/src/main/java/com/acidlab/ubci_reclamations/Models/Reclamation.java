package com.acidlab.ubci_reclamations.Models;



public class Reclamation {

    private float id;

    private String ticket_id = null;

    private String date;

    private String sujet;

    private String cost = null;

    private String file = null;

    private float nature_id;

    private float famille_id;

    private float local_id;

    private String fournisseur_id = null;

    private String rappel;

    private float rappel_nbr;

    private String priority_id;

    private String content;

    private String comment;

    private float entity_id;

    private String interlocuteur;

    private float status_id;

    private float user_id;

    private String clotured_at = null;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public String getDate() {
        return date;
    }

    public String getSujet() {
        return sujet;
    }

    public String getCost() {
        return cost;
    }

    public String getFile() {
        return file;
    }

    public float getNature_id() {
        return nature_id;
    }

    public float getFamille_id() {
        return famille_id;
    }

    public float getLocal_id() {
        return local_id;
    }

    public String getFournisseur_id() {
        return fournisseur_id;
    }

    public String getRappel() {
        return rappel;
    }

    public float getRappel_nbr() {
        return rappel_nbr;
    }

    public String getPriority_id() {
        return priority_id;
    }

    public String getContent() {
        return content;
    }

    public String getComment() {
        return comment;
    }

    public float getEntity_id() {
        return entity_id;
    }

    public String getInterlocuteur() {
        return interlocuteur;
    }

    public float getStatus_id() {
        return status_id;
    }

    public float getUser_id() {
        return user_id;
    }

    public String getClotured_at() {
        return clotured_at;
    }


    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setNature_id(float nature_id) {
        this.nature_id = nature_id;
    }

    public void setFamille_id(float famille_id) {
        this.famille_id = famille_id;
    }

    public void setLocal_id(float local_id) {
        this.local_id = local_id;
    }

    public void setFournisseur_id(String fournisseur_id) {
        this.fournisseur_id = fournisseur_id;
    }

    public void setRappel(String rappel) {
        this.rappel = rappel;
    }

    public void setRappel_nbr(float rappel_nbr) {
        this.rappel_nbr = rappel_nbr;
    }

    public void setPriority_id(String priority_id) {
        this.priority_id = priority_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setEntity_id(float entity_id) {
        this.entity_id = entity_id;
    }

    public void setInterlocuteur(String interlocuteur) {
        this.interlocuteur = interlocuteur;
    }

    public void setStatus_id(float status_id) {
        this.status_id = status_id;
    }

    public void setUser_id(float user_id) {
        this.user_id = user_id;
    }

    public void setClotured_at(String clotured_at) {
        this.clotured_at = clotured_at;
    }
}