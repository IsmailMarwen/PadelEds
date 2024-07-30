package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match,Long> {

}
