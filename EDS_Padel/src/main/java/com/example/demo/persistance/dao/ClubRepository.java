package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.AppWeb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<AppWeb,Long> {
}
