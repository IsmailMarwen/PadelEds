package com.example.demo.persistance.entities;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter

public class Terrain {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTerrain;
    private String nomTerrain;
    @ManyToOne
    private Club club;

}
