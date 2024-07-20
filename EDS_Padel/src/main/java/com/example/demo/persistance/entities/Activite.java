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
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String couleur;

    @OneToMany(mappedBy="activite",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<CategorieAbonnement> categorieAbonnements;

    @OneToMany(mappedBy="activite",fetch=FetchType.LAZY)
    private List<Ressource> ressources;
   

    @ManyToMany(mappedBy = "activites")
    @JsonIgnoreProperties("activites")
    private List<Club> clubs;
    @OneToMany(mappedBy="activite",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Materiel> materiels;
}

