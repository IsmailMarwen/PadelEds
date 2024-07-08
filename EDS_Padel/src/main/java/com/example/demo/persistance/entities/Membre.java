package com.example.demo.persistance.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Membre extends Utilisateur {

    private boolean validation;
    @OneToMany(mappedBy = "membre", fetch = FetchType.LAZY)
    private List<Notification> notifications;
}
