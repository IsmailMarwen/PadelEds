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

    @OneToMany(mappedBy = "activite", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CategorieAbonnement> categorieAbonnements;

    @ManyToMany
    @JoinTable(
            name = "ressource_activite",
            joinColumns = @JoinColumn(name = "activite_id"),
            inverseJoinColumns = @JoinColumn(name = "ressource_id")
    )
    @JsonIgnore
    private List<Ressource> ressources;

    @ManyToMany(mappedBy = "activites")
    @JsonIgnoreProperties("activites")
    private List<Club> clubs;
}

