package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.Coach;

import java.util.List;

public interface ICoach {
    Coach saveCoach(Coach coach);
    Coach updateCoach(Coach coach);
    boolean deleteCoach(Long id);
    List<Coach> getListCoach();
    Coach getCoachByIdCoach(Long id);
}
