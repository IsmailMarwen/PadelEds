package com.example.demo.persistance.entities;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Medecin implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }



    private String adr;
    @JsonIgnoreProperties("medecins")
    @ManyToOne
    @JoinColumn(name = "specialitie_id")
    private Speciality specialitie;

    public Speciality getSpecialitie() {
        return specialitie;
    }

    public void setSpecialitie(Speciality specialitie) {
        this.specialitie = specialitie;
    }
    @JsonIgnore
    @OneToMany(mappedBy="medecin", fetch=FetchType.EAGER ,cascade = CascadeType.ALL)
    private List<Rendezvous> listrdv;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Rendezvous> getListrdv() {
        return listrdv;
    }

    public void setListrdv(List<Rendezvous> listrdv) {
        listrdv = listrdv;
    }


}