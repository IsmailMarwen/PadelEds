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
public class PlageHoraire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String debutAct;
    private String finAct;
    private String dureeAct;
    @OneToMany(mappedBy="plageHoraire",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Ressource> ressources;
    @ManyToOne
    private Club club;
    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reservation> reservations;
}
