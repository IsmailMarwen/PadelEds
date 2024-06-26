package com.example.demo.persistance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @ManyToOne
    private Club club;
    @OneToMany(mappedBy="activite",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<CategorieAbonnement> categorieAbonnements;
}
