package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.ClubRepository;
import com.example.demo.persistance.entities.Club;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.interfaces.IClub;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class ClubService implements  IClub {
    @Autowired
    public ClubRepository clubRepository;
    @Override
    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    @Override
    public Club updateClub(Club Club) {
        return clubRepository.saveAndFlush(Club);
    }

    @Override
    public boolean deleteClub(Long id) {
        clubRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Club> getListClub() {
        return clubRepository.findAll();
    }

    @Override
    public Club getClubByIdClub(Long id) {
        return clubRepository.findById(id).get();
    }

    @Override
    public List<Club> getClubsNearLocation(double latitude, double longitude, double distance) {
        return clubRepository.findClubsNearLocation(latitude, longitude, distance);

    }

    @Override
    public List<Club> getClubsByNomClub(String nomClub) {
        return  clubRepository.findByNomClubContainingIgnoreCase(nomClub);
    }

    @Override
    public List<Club> getClubsByVille(String ville) {
        return clubRepository.findByVille(ville);
    }
}

