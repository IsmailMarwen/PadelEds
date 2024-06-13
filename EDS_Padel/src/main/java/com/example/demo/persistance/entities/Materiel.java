package com.example.demo.persistance.entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Materiel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMateriel;
    private String reference;
    private String image;
    private double prix;
    private int nb;
    @ManyToOne
    private  Club club;

}
