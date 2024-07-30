package com.example.demo.persistance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Membre extends Utilisateur {

    private boolean validation;
    @OneToMany(mappedBy = "membre", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Notification> notifications;
    @ManyToMany
    @JoinTable(
            name = "membre_type_abonnement",
            joinColumns = @JoinColumn(name = "membre_id"),
            inverseJoinColumns = @JoinColumn(name = "type_abonnement_id")
    )
    private List<TypeAbonnementClub> typeAbonnements;
    @OneToMany(mappedBy = "membre", fetch = FetchType.LAZY)
    private List<Reservation> reservations;
    @ManyToMany
    @JoinTable(
            name = "membre_match",
            joinColumns = @JoinColumn(name = "membre_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    @JsonIgnore
    private List<Match> matchs;
}
