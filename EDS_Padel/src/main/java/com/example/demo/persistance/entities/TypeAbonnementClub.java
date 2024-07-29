package com.example.demo.persistance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class TypeAbonnementClub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libType;
    private int nbMois;
    private int nbJours;
    private double forfait;
    private double remise;

    @ManyToOne
    private  Club club;
    @ManyToOne
    @JoinColumn(name = "categorie_abonnement_id") // Optionnel mais recommandé pour la clarté
    private CategorieAbonnement categorieAbonnement;
    @ManyToMany(mappedBy = "typeAbonnements")
    private List<Coach> coaches ;

    @ManyToMany(mappedBy = "typeAbonnements")
    private List<Membre> membres ;
}
