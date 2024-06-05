package com.example.demo.persistance.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Rendezvous implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateRDV;
    private String heureRDV;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Patient patient;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Medecin medecin;
}