package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Medecin;

public class RendezvousDetails {
    private Long rendezvousId;
    private String rendezvousDate;
    private String rendezvousHeure;
    private Medecin medcin;

    public Long getRendezvousId() {
        return rendezvousId;
    }

    public void setRendezvousId(Long rendezvousId) {
        this.rendezvousId = rendezvousId;
    }

    public String getRendezvousDate() {
        return rendezvousDate;
    }

    public void setRendezvousDate(String rendezvousDate) {
        this.rendezvousDate = rendezvousDate;
    }

    public String getRendezvousHeure() {
        return rendezvousHeure;
    }

    public void setRendezvousHeure(String rendezvousHeure) {
        this.rendezvousHeure = rendezvousHeure;
    }

    public Medecin getMedcin() {
        return medcin;
    }

    public void setMedcin(Medecin medcin) {
        this.medcin = medcin;
    }
}
