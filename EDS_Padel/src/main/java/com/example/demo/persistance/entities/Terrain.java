package com.example.demo.persistance.entities;
import javax.persistence.*;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor


public class Terrain {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTerrain;
    private String nomTerrain;

    public int getIdTerrain() {
        return idTerrain;
    }

    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
    }
}
