package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach,Long> {
}
