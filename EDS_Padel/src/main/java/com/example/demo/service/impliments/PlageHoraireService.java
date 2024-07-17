package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.PlageHoraireRepository;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.PlageHoraire;
import com.example.demo.service.interfaces.IPlageHoraire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PlageHoraireService implements IPlageHoraire {
    @Autowired
    public PlageHoraireRepository plageHoraireRepository;
    @Autowired
    public ClubService clubService;
    @Override
    public PlageHoraire savePlageHoraire(PlageHoraire plageHoraire) {
        return plageHoraireRepository.save(plageHoraire);
    }

    @Override
    public PlageHoraire updatePlageHoraire(PlageHoraire plageHoraire) {
        return plageHoraireRepository.saveAndFlush(plageHoraire);
    }

    @Override
    public boolean deletePlageHoraire(Long id) {
        plageHoraireRepository.deleteById(id);
        return true;
    }

    @Override
    public List<PlageHoraire> getListPlageHoraire() {
        return plageHoraireRepository.findAll();
    }

    @Override
    public PlageHoraire getPlageHoraireByIdPlageHoraire(Long id) {
        return plageHoraireRepository.findById(id).get();
    }

    @Override
    public List<PlageHoraire> getListPlageHoraireByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return plageHoraireRepository.getAllByClub(club);
    }
}
