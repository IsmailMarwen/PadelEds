package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.TypeDepense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeDepenseRepository extends JpaRepository<TypeDepense,Long> {
    List<TypeDepense> getAllByClub(Club club);
}
