package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.MatchRepository;
import com.example.demo.persistance.entities.MatchDetail;
import com.example.demo.service.interfaces.IMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService implements IMatch {
    @Autowired
    MatchRepository matchRepository;
    @Override
    public MatchDetail saveMatch(MatchDetail match) {
        return matchRepository.save(match);
    }

    @Override
    public MatchDetail updateMatch(MatchDetail match) {
        return matchRepository.saveAndFlush(match);
    }

    @Override
    public boolean deleteMatch(Long id) {
        matchRepository.deleteById(id);
        return true;
    }

    @Override
    public List<MatchDetail> getAll() {
        return matchRepository.findAll();
    }
}
