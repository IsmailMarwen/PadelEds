package com.example.demo.persistance.dao;

import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByAdmin(Administrateur administrateur);
}
