package com.example.demo.persistance.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SuperAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nom;
    private  String prenom;
    private String email;
    private String password;
    private String telephone;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;


}
