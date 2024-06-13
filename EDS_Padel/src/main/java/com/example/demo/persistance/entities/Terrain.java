package com.example.demo.persistance.entities;
import javax.persistence.*;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor


public class Terrain {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTerrain;
    private String nomTerrain;

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @ManyToOne
    private Club club;

    public long getIdTerrain() {
        return idTerrain;
    }

    public void setIdTerrain(long idTerrain) {
        this.idTerrain = idTerrain;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
    }
}
