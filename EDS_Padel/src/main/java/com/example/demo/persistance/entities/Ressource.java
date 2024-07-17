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
public class Ressource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String capacite;
    @ManyToOne
    private Club club;
    @ManyToMany
    @JoinTable(
            name = "ressource_activite",
            joinColumns = @JoinColumn(name = "ressource_id"),
            inverseJoinColumns = @JoinColumn(name = "activite_id")
    )
    private List<Activite> activites;
    @OneToMany(mappedBy="ressource",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Reservation> reservations;
    @ManyToOne
    private PlageHoraire plageHoraire;
}
