package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin,Long> {
    SuperAdmin findByEmailAndPassword(String email,String password);
    SuperAdmin findByEmail(String email);
    SuperAdmin findByTelephone(String tel);

}
