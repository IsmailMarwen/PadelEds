package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Match;

import java.util.List;

public interface IMatch {
    Match saveMatch(Match match);
    Match updateMatch(Match match);
    boolean deleteMatch(Long id);
    
}
