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
public class TypeAbonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String codeTypeAbonnement;
    private  String libTypeAbonnement;
    private String mthtTypeAbonnement;
    private String tauxTva;
    private String mtttc;
    private String nbMois;
    private String couleur;
    private String nbUtilisateur;
    private String nbJoursGratuit;
    private String remise;

    @OneToMany(mappedBy="typeAbonnement",fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Club> clubs;

}
