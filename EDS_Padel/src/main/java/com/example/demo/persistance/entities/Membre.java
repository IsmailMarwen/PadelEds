package com.example.demo.persistance.entities;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Membre extends Utilisateur {

    private boolean validation;
}
