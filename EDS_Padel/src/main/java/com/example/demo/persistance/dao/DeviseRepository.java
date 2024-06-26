package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviseRepository extends JpaRepository<Devise,Long> {
}
