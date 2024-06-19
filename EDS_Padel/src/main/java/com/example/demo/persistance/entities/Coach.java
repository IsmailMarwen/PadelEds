package com.example.demo.persistance.entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Coach extends Utilisateur {
    private  double salaireHoraire;
    private boolean validation;
}
