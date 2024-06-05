package com.example.demo.service.interfaces;

import com.example.demo.persistance.dao.RendezvousDetails;
import com.example.demo.persistance.entities.Rendezvous;

import java.util.List;

public interface IRendezvous {
    Rendezvous saveRendezvous(Rendezvous rendezvous);
    Rendezvous updateRendezvous(Rendezvous rendezvous);
    boolean deleteRendezvous(Long id);
    List<Rendezvous> getListRendezvous();
    Rendezvous getRendezvous(Long id);
    int getQuantityOfRendezvous();
    Rendezvous getRendezvousById(Long id);
    List<Rendezvous> getRendezvousByPatient(Long patientId);


}
