package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.RessourceRepository;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Ressource;
import com.example.demo.service.interfaces.IRessource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RessourceService implements IRessource {
    @Autowired
    public RessourceRepository ressourceRepository;
    @Autowired
    public ClubService clubService;
    @Override
    public Ressource saveRessource(Ressource ressource) {
        return ressourceRepository.save(ressource);
    }

    @Override
    public Ressource updateRessource(Ressource ressource) {

        return ressourceRepository.saveAndFlush(ressource);
    }
    @Override
    public boolean deleteRessource(Long id) {
        ressourceRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Ressource> getListRessource() {
        return ressourceRepository.findAll();
    }

    @Override
    public Ressource getRessourceByIdRessource(Long id) {
        return ressourceRepository.findById(id).get();
    }

    @Override
    public List<Ressource> getListRessourceByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return ressourceRepository.getAllByClub(club);
    }
}
