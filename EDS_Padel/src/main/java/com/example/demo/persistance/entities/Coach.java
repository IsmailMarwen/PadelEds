package com.example.demo.persistance.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Coach extends Utilisateur {
    private  double salaireHoraire;
    private boolean validation;
    @OneToMany(mappedBy = "coach", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Notification> notifications;
    @OneToMany(mappedBy = "coach", fetch = FetchType.LAZY)
    private List<Reservation> reservations;
    @ManyToMany
    @JoinTable(
            name = "coach_type_abonnement",
            joinColumns = @JoinColumn(name = "coach_id"),
            inverseJoinColumns = @JoinColumn(name = "type_abonnement_id")
    )
    private List<TypeAbonnementClub> typeAbonnements ;
    @ManyToMany
    @JoinTable(
            name = "coach_match",
            joinColumns = @JoinColumn(name = "coach_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    @JsonIgnore
    private List<MatchDetail> matchs;
}
