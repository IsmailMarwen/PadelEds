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
    @JsonIgnore
    private Club club;
    @ManyToOne
    @JsonIgnore
    private Activite activite;
    @OneToMany(mappedBy="ressource",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Reservation> reservations;
    @ManyToOne
    private PlageHoraire plageHoraire;
}
