package com.example.demo.persistance.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Patient implements Serializable{

	  public Patient() {
		super();
	}

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

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Patient(Long id, String nom, String prenom, String adr, String email, List<Rendezvous> rendezvousList) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adr = adr;
		this.email = email;
		this.rendezvousList = rendezvousList;
	}

	public List<Rendezvous> getRendezvousList() {
		return rendezvousList;
	}
	public void setRendezvousList(List<Rendezvous> rendezvousList) {
		this.rendezvousList = rendezvousList;
	}
	@Id 
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  private String nom;
	  private String prenom;
	  private String adr;
	  private String email;

	  @JsonIgnore
	  @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	  private List<Rendezvous> rendezvousList;


}
