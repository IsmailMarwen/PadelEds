package com.example.demo.persistance.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Consultation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCons;
    private LocalDateTime dateCons;
    private String recapCons;

    public Long getIdCons() {
        return idCons;
    }

    public void setIdCons(Long idCons) {
        this.idCons = idCons;
    }

    public LocalDateTime getDateCons() {
        return dateCons;
    }

    public void setDateCons(LocalDateTime dateCons) {
        this.dateCons = dateCons;
    }

    public String getRecapCons() {
        return recapCons;
    }

    public void setRecapCons(String recapCons) {
        this.recapCons = recapCons;
    }
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Rendezvous rendezvous;
}

