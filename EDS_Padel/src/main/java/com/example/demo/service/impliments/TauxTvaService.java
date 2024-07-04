package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.TauxTvaRepository;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.TauxTva;
import com.example.demo.service.interfaces.ITauxTva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TauxTvaService implements ITauxTva {
    @Autowired
    public TauxTvaRepository tauxTvaRepository;
    @Autowired
    public ClubService clubService;
    @Override
    public TauxTva saveTauxTva(TauxTva tauxTva) {
        return tauxTvaRepository.save(tauxTva);
    }

    @Override
    public TauxTva updateTauxTva(TauxTva tauxTva) {

        return tauxTvaRepository.saveAndFlush(tauxTva);
    }
    @Override
    public boolean deleteTauxTva(Long id) {
        tauxTvaRepository.deleteById(id);
        return true;
    }

    @Override
    public List<TauxTva> getListTauxTva() {
        return tauxTvaRepository.findAll();
    }

    @Override
    public TauxTva getTauxTvaByIdTauxTva(Long id) {
        return tauxTvaRepository.findById(id).get();
    }

    @Override
    public List<TauxTva> getListTauxTvaByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return tauxTvaRepository.getAllByClub(club);
    }
}
