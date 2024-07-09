package com.example.demo.service.impliments;
import com.example.demo.persistance.dao.NotificationRepository;
import com.example.demo.persistance.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AdministrateurService administrateurService;
    @Autowired
    private AgentAcceuilService agentAcceuilService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void notifyAdmins(Club club, String message, Coach coach, Membre membre) {
        List<Administrateur> admins = administrateurService.getListAdminstarteurByClub(club.getIdClub());
        List<AgentAcceuil> agentAcceuils=agentAcceuilService.getListAgentAcceuilByClub(club.getIdClub());
        for (Administrateur admin : admins) {
            Notification notification = new Notification();
            notification.setMessage(message);
            notification.setDate(LocalDateTime.now());
            notification.setRead(false);
            notification.setAdmin(admin);
            notification.setCoach(coach);
            notification.setMembre(membre);
            notificationRepository.save(notification);
            messagingTemplate.convertAndSend( "/queue/notifications", message);
        }
        for (AgentAcceuil agentAcceuil : agentAcceuils) {
            Notification notification = new Notification();
            notification.setMessage(message);
            notification.setDate(LocalDateTime.now());
            notification.setRead(false);
            notification.setAgentAcceuil(agentAcceuil);
            notification.setCoach(coach);
            notification.setMembre(membre);
            notificationRepository.save(notification);
            messagingTemplate.convertAndSend( "/queue/notifications", message);

        }
    }
    public List<Notification> getNotificationsForAdmin(Administrateur administrateur) {
        return notificationRepository.findByAdmin(administrateur);
    }
    public List<Notification> getNotificationsForAgent(AgentAcceuil agentAcceuil) {
        return notificationRepository.findByAgentAcceuil(agentAcceuil);
    }
    }



