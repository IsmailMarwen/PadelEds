package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Banque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanqueRepository extends JpaRepository<Banque,Long> {
}
