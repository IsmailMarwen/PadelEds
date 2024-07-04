package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.TournoiRepository;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Tournoi;
import com.example.demo.service.interfaces.ITournoi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
    public class TournoiService implements ITournoi {
    @Autowired
    public TournoiRepository tournoiRepository;
    @Autowired
    public ClubService clubService;
    @Override
    public Tournoi saveTournoi(Tournoi tournoi) {
        return tournoiRepository.save(tournoi);

    }

    @Override
    public Tournoi updateTournoi(Tournoi tournoi) {
        return tournoiRepository.saveAndFlush(tournoi);
    }

    @Override
    public boolean deleteTournoi(Long id) {
        tournoiRepository.deleteById(id);
        return true;

    }

    @Override
    public List<Tournoi> getListTournoi() {
        return tournoiRepository.findAll();
    }

    @Override
    public Tournoi getTournoiByIdTournoi(Long id) {
        return tournoiRepository.findById(id).get();

    }

    @Override
    public List<Tournoi> getListTournoiByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return tournoiRepository.getAllByClub(club);
    }


}
