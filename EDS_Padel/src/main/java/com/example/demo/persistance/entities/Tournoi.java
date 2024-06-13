package com.example.demo.persistance.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Tournoi {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idTournoi;
        private String nomTournoi;
        private String typeTournoi;
        private Date dateDebut;
        private Date dateFin;
        private int prixParticipation;
        @ManyToOne
        private Club club;






}
