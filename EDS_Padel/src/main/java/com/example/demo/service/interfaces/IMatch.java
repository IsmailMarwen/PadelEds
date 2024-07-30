package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.MatchDetail;

import java.util.List;

public interface IMatch {
    MatchDetail saveMatch(MatchDetail match);
    MatchDetail updateMatch(MatchDetail match);
    boolean deleteMatch(Long id);
    List<MatchDetail> getAll();
}
