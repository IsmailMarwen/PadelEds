package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.CoachRepository;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Coach;
import com.example.demo.service.interfaces.ICoach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService implements ICoach {
    @Autowired
    public CoachRepository coachRepository;
    @Autowired
    public  ClubService clubService;

    @Override
    public Coach saveCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    @Override
    public Coach updateCoach(Coach coach) {
        return coachRepository.saveAndFlush(coach);
    }

    @Override
    public boolean deleteCoach(Long id) {
        coachRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Coach> getListCoach() {
        return coachRepository.findAll();
    }

    @Override
    public Coach getCoachByIdCoach(Long id) {
        return coachRepository.findById(id).get();
    }

    @Override
    public List<Coach> getCoachsByNom(String nom) {
        return coachRepository.findByNom(nom);
    }

    @Override
    public List<Coach> getListCoachByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return coachRepository.getAllByClub(club);
    }
}
