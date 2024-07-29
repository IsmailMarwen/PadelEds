package com.example.demo.persistance.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateRes;
    private String heureRes;
    private String dateDernierRes;
    private String heureDebut;
    private String heureFin;

    @ManyToOne
    private Ressource ressource;
    @ManyToOne
    private Club club;
    @ManyToOne
    private PlageHoraire plageHoraire;

}
