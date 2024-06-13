package com.example.demo.persistance.entities;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor

public class Materiel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMateriel;
    private String reference;
    private String image;
    private double prix;
    private int nb;



    @ManyToOne
    private  Club club;
    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
    public long getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(long idMateriel) {
        this.idMateriel = idMateriel;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
}
