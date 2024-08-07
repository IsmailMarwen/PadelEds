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
public class MatchDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idReservation", referencedColumnName = "id")
    private Reservation reservation;
    @ManyToMany
    @JoinTable(
            name = "coach_match",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "coach_id")
    )
    private List<Coach> coaches;
    @ManyToMany
    @JoinTable(
            name = "membre_match",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "membre_id")
    )
    private List<Membre> membres;

    @Override
    public String toString() {
        return "MatchDetail{" +
                "id=" + id +
                '}';
    }
}
