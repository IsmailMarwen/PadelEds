package com.example.demo.service.impliments;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.persistance.dao.*;
import com.example.demo.persistance.entities.*;
import com.example.demo.service.interfaces.*;
@Service
public class RendezvousService implements IRendezvous {
    @Autowired
    public RendezvousRepository rendezvousrepository;
    @Override
    public Rendezvous saveRendezvous(Rendezvous rendezvous) {
        return rendezvousrepository.save(rendezvous);
    }

    @Override
    public Rendezvous updateRendezvous(Rendezvous rendezvous) {
        return rendezvousrepository.saveAndFlush(rendezvous);
    }

    @Override
    public boolean deleteRendezvous(Long id) {
        rendezvousrepository.deleteById(id);
        return true;
    }

    @Override
    public List<Rendezvous> getListRendezvous() {
        return rendezvousrepository.findAll();
    }

    @Override
    public Rendezvous getRendezvous(Long id) {
        return rendezvousrepository.findById(id).get();
    }

    @Override
    public int getQuantityOfRendezvous() {
        return rendezvousrepository.getQuantityOfRendezvous();
    }

    @Override
    public Rendezvous getRendezvousById(Long id) {
        return null;
    }

    @Override
    public List<Rendezvous> getRendezvousByPatient(Long patientId) {
        return rendezvousrepository.findByPatientId(patientId);


    }
}
