package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.MatchDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchDetail,Long> {

}
