package com.example.demo.persistance.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materiel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMateriel;
    private String reference;
    private String image;
    private double prix;
    private int nb;
}
