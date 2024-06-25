package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin,Long> {
}
