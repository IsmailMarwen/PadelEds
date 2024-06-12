package com.example.demo.persistance.entities;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idClub;
    private  String nomClub;
    private String email;
    private  double latitude ;
    private  double longitude ;
    private String codePostal;
    private  String ville;
    private String pays;
    private  String fuseau ;
    private String horaireInfos;
    private  String telPrincipal;
    private  String telSecondaire;
    private String activite;
    private  int nbTerrain ;
    private String offre;
    private  boolean payement;
    private  String matriculeFiscale;
    @OneToOne
    private AppWeb appWeb;

    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    public String getNomClub() {
        return nomClub;
    }

    public void setNomClub(String nomClub) {
        this.nomClub = nomClub;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getFuseau() {
        return fuseau;
    }

    public void setFuseau(String fuseau) {
        this.fuseau = fuseau;
    }

    public String getHoraireInfos() {
        return horaireInfos;
    }

    public void setHoraireInfos(String horaireInfos) {
        this.horaireInfos = horaireInfos;
    }

    public String getTelPrincipal() {
        return telPrincipal;
    }

    public void setTelPrincipal(String telPrincipal) {
        this.telPrincipal = telPrincipal;
    }

    public String getTelSecondaire() {
        return telSecondaire;
    }

    public void setTelSecondaire(String telSecondaire) {
        this.telSecondaire = telSecondaire;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public int getNbTerrain() {
        return nbTerrain;
    }

    public void setNbTerrain(int nbTerrain) {
        this.nbTerrain = nbTerrain;
    }

    public String getOffre() {
        return offre;
    }

    public void setOffre(String offre) {
        this.offre = offre;
    }

    public boolean isPayement() {
        return payement;
    }

    public void setPayement(boolean payement) {
        this.payement = payement;
    }

    public String getMatriculeFiscale() {
        return matriculeFiscale;
    }

    public void setMatriculeFiscale(String matriculeFiscale) {
        this.matriculeFiscale = matriculeFiscale;
    }

    public AppWeb getAppWeb() {
        return appWeb;
    }

    public void setAppWeb(AppWeb appWeb) {
        this.appWeb = appWeb;
    }


}
