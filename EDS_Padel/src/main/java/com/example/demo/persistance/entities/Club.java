package com.example.demo.persistance.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idClub;
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
    @OneToOne(mappedBy = "club")
    @JsonManagedReference
    private AppWeb appWeb;
    @OneToMany(mappedBy="club",fetch=FetchType.LAZY)
    private List<Administrateur> administrateurs;
    @OneToMany(mappedBy="club",fetch=FetchType.LAZY)
    private List<Membre> membres;

    public List<Administrateur> getAdministrateurs() {
        return administrateurs;
    }

    public void setAdministrateurs(List<Administrateur> administrateurs) {
        this.administrateurs = administrateurs;
    }

    public List<Membre> getMembres() {
        return membres;
    }

    public void setMembres(List<Membre> membres) {
        this.membres = membres;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    @OneToMany(mappedBy="club",fetch=FetchType.LAZY)
    private List<Coach> coaches;
    @OneToMany(mappedBy="club",fetch=FetchType.LAZY)
    private List<Materiel> materiels;

    public List<Materiel> getMateriels() {
        return materiels;
    }

    public void setMateriels(List<Materiel> materiels) {
        this.materiels = materiels;
    }

    public List<Terrain> getTerrains() {
        return terrains;
    }

    public void setTerrains(List<Terrain> terrains) {
        this.terrains = terrains;
    }

    public List<Tournoi> getTournois() {
        return tournois;
    }

    public void setTournois(List<Tournoi> tournois) {
        this.tournois = tournois;
    }

    @OneToMany(mappedBy="club",fetch=FetchType.LAZY)
    private List<Terrain> terrains;
    @OneToMany(mappedBy="club",fetch=FetchType.LAZY)
    private List<Tournoi> tournois;
    public long getIdClub() {
        return idClub;
    }

    public void setIdClub(long idClub) {
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
