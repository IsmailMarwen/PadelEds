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
    @Lob
    @Column(name = "image", length = 1048576000)
    private String image;
    private double prix;
    private int nb;
    @ManyToOne
    private  Club club;
    @ManyToOne
    private Activite activite;

}
