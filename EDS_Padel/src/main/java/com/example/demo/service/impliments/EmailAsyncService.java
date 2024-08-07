package com.example.demo.service.impliments;
import com.example.demo.persistance.entities.Membre;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class EmailAsyncService {

    private final EmailService emailService;

    public EmailAsyncService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Async
    public void sendEmails(List<Membre> membres, String subject, String htmlBody) {
        for (Membre membre : membres) {
            try {
                emailService.sendHtmlMessage(membre.getEmail(), subject, htmlBody);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
