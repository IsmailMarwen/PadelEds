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
        private String dateDebut;
        private String dateFin;
        private int prixParticipation;
        private int nbreParticipant
        @ManyToOne
        private Club club;
        @ManyToOne
        private Activite activite;





}
