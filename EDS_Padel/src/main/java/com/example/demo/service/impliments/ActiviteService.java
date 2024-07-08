package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.ActiviteRepository;
import com.example.demo.persistance.entities.Activite;
import com.example.demo.service.interfaces.IActivite;
import com.example.demo.service.interfaces.IActivite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service

public class ActiviteService implements IActivite {
    @Autowired
    public ActiviteRepository activiteRepository;
    @Override
    public Activite saveActivite(Activite activite) {
        if(activiteRepository.existsActiviteByCouleur(activite.getCouleur())){
            return null;
        }
        if(activiteRepository.existsActiviteByLibelle(activite.getLibelle())){
            return null;
        }
        return activiteRepository.save(activite);
    }

    @Override
    public Activite updateActivite(Activite activite) {

        if(activiteRepository.existsActiviteByCouleur(activite.getCouleur())){
            return null;
        }
        if(activiteRepository.existsActiviteByLibelle(activite.getLibelle())){
            return null;
        }
        return activiteRepository.saveAndFlush(activite);
    }
    @Override
    public boolean deleteActivite(Long id) {
        activiteRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Activite> getListActivite() {
        return activiteRepository.findAll();
    }

    @Override
    public Activite getActiviteByIdActivite(Long id) {
        return activiteRepository.findById(id).get();
    }
}
