package com.example.demo.persistance.entities;

import javax.persistence.*;

import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
public class Tournoi {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long idTournoi;
        private String nomTournoi;
        private String typeTournoi;
        private Date dateDebut;
        private Date dateFin;

        private int prixParticipation;

        public Club getClub() {
                return club;
        }

        public void setClub(Club club) {
                this.club = club;
        }

        @ManyToOne
        private Club club;

        public long getIdTournoi() {
                return idTournoi;
        }

        public void setIdTournoi(long idTournoi) {
                this.idTournoi = idTournoi;
        }

        public String getNomTournoi() {
                return nomTournoi;
        }

        public void setNomTournoi(String nomTournoi) {
                this.nomTournoi = nomTournoi;
        }

        public String getTypeTournoi() {
                return typeTournoi;
        }

        public void setTypeTournoi(String typeTournoi) {
                this.typeTournoi = typeTournoi;
        }

        public Date getDateDebut() {
                return dateDebut;
        }

        public void setDateDebut(Date dateDebut) {
                this.dateDebut = dateDebut;
        }

        public Date getDateFin() {
                return dateFin;
        }

        public void setDateFin(Date dateFin) {
                this.dateFin = dateFin;
        }

        public int getPrixParticipation() {
                return prixParticipation;
        }

        public void setPrixParticipation(int prixParticipation) {
                this.prixParticipation = prixParticipation;
        }





}
