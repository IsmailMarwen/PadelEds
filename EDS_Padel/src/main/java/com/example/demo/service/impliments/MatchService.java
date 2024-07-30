package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.MatchRepository;
import com.example.demo.persistance.entities.Match;
import com.example.demo.service.interfaces.IMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService implements IMatch {
    @Autowired
    MatchRepository matchRepository;
    @Override
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public Match updateMatch(Match match) {
        return matchRepository.saveAndFlush(match);
    }

    @Override
    public boolean deleteMatch(Long id) {
        matchRepository.deleteById(id);
        return true;
    }
}
