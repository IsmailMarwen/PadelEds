package com.example.demo.persistance.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class AppWeb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAppWeb;
    private  String nomAppWeb;
    private String logoAppWeb;
    private  String couleurAppWeb;
    private String bannerImage;
    private  String adresseUrl;
    private String mode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idClub", referencedColumnName = "idClub")
    @JsonIgnoreProperties({"appWeb"})
    private Club club;
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCouleurSideBar() {
        return couleurSideBar;
    }

    public void setCouleurSideBar(String couleurSideBar) {
        this.couleurSideBar = couleurSideBar;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }


    private String couleurSideBar;

    public long getIdAppWeb() {
        return idAppWeb;
    }

    public void setIdAppWeb(long idAppWeb) {
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
