package com.example.demo.persistance.entities;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;
    @Lob
    @Column(name = "image", length = 1048576000)
    private String image;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String genre;
    private String username;
    private String password;
    private String role;
    private Boolean updated;
    @ManyToOne
    private Club club;

}
