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
    @ManyToMany(mappedBy = "activites")
    private List<Ressource> ressources;
}
