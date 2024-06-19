package com.example.demo.persistance.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClub;
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
    private  String adresse;
    @OneToOne(mappedBy = "club")
    @JsonIgnoreProperties("club")
    private AppWeb appWeb;
    @OneToMany(mappedBy="club",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Administrateur> administrateurs;
    @OneToMany(mappedBy="club",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Membre> membres;
    @OneToMany(mappedBy="club",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Coach> coaches;
    @OneToMany(mappedBy="club",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Materiel> materiels;
    @OneToMany(mappedBy="club",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Terrain> terrains;
    @OneToMany(mappedBy="club",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Tournoi> tournois;




}
