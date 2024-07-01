package com.example.demo.persistance.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
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
