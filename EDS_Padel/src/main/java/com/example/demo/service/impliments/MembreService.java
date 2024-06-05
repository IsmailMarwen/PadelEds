package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.MembreRepository;
import com.example.demo.persistance.entities.Membre;
import com.example.demo.service.interfaces.IMembre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MembreService implements IMembre {
    @Autowired
    public MembreRepository membreRepository;

    @Override
    public Membre saveMembre(Membre membre) {
        return membreRepository.save((membre));
    }

    @Override
    public Membre updateMembre(Membre membre) {
        return membreRepository.saveAndFlush(membre);
    }

    @Override
    public boolean deleteMembre(Long id) {
        membreRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Membre> getListMembre() {
        return membreRepository.findAll();
    }

    @Override
    public Membre getMembreByIdMembre(Long id) {
        return membreRepository.findById(id).get();
    }
}
