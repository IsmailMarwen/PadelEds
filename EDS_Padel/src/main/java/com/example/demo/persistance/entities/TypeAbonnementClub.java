package com.example.demo.persistance.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "categorie_abonnement_id") // Optionnel mais recommandé pour la clarté
    private CategorieAbonnement categorieAbonnement;
}
