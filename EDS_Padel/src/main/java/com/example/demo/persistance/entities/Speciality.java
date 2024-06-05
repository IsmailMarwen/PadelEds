package com.example.demo.persistance.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Speciality implements Serializable {
	
	
	
	    @Id 
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String specialityName;

	@JsonIgnore
	@OneToMany(mappedBy = "specialitie", fetch = FetchType.LAZY)
	private List<Medecin> medecins;




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public List<Medecin> getMedecins() {
		return medecins;
	}

	public void setMedecins(List<Medecin> medecins) {
		this.medecins = medecins;
	}
}
