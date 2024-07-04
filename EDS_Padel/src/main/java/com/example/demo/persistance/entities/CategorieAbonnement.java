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
public class CategorieAbonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategorie;
    private String designation;

    @ManyToOne
    private Activite activite;
    @ManyToOne
    private  Club club;
    @OneToMany(mappedBy = "categorieAbonnement", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TypeAbonnementClub> typeAbonnementClubs;
}
