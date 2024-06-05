package com.example.demo.persistance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppWeb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAppWeb;
    private  String nomAppWeb;
    private String logoAppWeb;
    private  String couleurAppWeb;
    private String bannerImage;
    private  String adresseUrl;

    public int getIdAppWeb() {
        return idAppWeb;
    }

    public void setIdAppWeb(int idAppWeb) {
        this.idAppWeb = idAppWeb;
    }

    public String getNomAppWeb() {
        return nomAppWeb;
    }

    public void setNomAppWeb(String nomAppWeb) {
        this.nomAppWeb = nomAppWeb;
    }

    public String getLogoAppWeb() {
        return logoAppWeb;
    }

    public void setLogoAppWeb(String logoAppWeb) {
        this.logoAppWeb = logoAppWeb;
    }

    public String getCouleurAppWeb() {
        return couleurAppWeb;
    }

    public void setCouleurAppWeb(String couleurAppWeb) {
        this.couleurAppWeb = couleurAppWeb;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getAdresseUrl() {
        return adresseUrl;
    }

    public void setAdresseUrl(String adresseUrl) {
        this.adresseUrl = adresseUrl;
    }
}
