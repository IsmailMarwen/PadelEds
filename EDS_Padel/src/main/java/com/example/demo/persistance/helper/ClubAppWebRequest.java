package com.example.demo.persistance.helper;

import com.example.demo.persistance.entities.AppWeb;
import com.example.demo.persistance.entities.Club;

public class ClubAppWebRequest {
    private Club club;
    private AppWeb appWeb;
    private String nom;
    private String prenom;
    private String genre;
    private String image;
    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public AppWeb getAppWeb() {
        return appWeb;
    }

    public void setAppWeb(AppWeb appWeb) {
        this.appWeb = appWeb;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
