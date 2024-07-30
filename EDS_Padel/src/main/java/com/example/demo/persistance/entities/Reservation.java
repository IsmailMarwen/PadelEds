package com.example.demo.persistance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnore
    private Club club;
    @ManyToOne
    @JsonIgnore
    private PlageHoraire plageHoraire;
    @ManyToOne
    private Coach coach;
    @ManyToOne
    private Membre membre;
    @ManyToOne
    private Administrateur admin;
    @ManyToOne
    private AgentAcceuil agentAcceuil;
    @OneToOne(mappedBy = "reservation")
    @JsonIgnoreProperties("reservation")
    private MatchDetail match;
}
