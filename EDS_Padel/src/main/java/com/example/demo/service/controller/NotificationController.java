package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.AgentAcceuil;
import com.example.demo.persistance.entities.Notification;
import com.example.demo.service.impliments.AdministrateurService;
import com.example.demo.service.impliments.AgentAcceuilService;
import com.example.demo.service.impliments.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private AdministrateurService administrateurService;
    @Autowired
    private AgentAcceuilService agentAcceuilService;
    @GetMapping("/admin/{adminId}")
    public List<Notification> getNotificationsForAdmin(@PathVariable Long adminId) {
        Administrateur admin = administrateurService.getAdminstarteurByIdAdminstarteur(adminId); // Assuming you have this method in your service
        return notificationService.getNotificationsForAdmin(admin);
    }
    @GetMapping("/agent/{agentId}")
    public List<Notification> getNotficationsForAgent(@PathVariable Long agentId) {
        AgentAcceuil agentAcceuil = agentAcceuilService.getAgentAcceuilByIdAgentAcceuil(agentId); // Assuming you have this method in your service
        return notificationService.getNotificationsForAgent(agentAcceuil);
    }
}
