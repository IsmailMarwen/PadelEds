package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.Notification;
import com.example.demo.service.impliments.AdministrateurService;
import com.example.demo.service.impliments.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private AdministrateurService administrateurService;
    @GetMapping("/admin/{adminId}")
    public List<Notification> getNotificationsForAdmin(@PathVariable Long adminId) {
        Administrateur admin = administrateurService.getAdminstarteurByIdAdminstarteur(adminId); // Assuming you have this method in your service
        return notificationService.getNotificationsForAdmin(admin);
    }
}
