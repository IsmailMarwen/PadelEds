package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.*;
import com.example.demo.persistance.entities.AppWeb;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Coach;
import com.example.demo.service.interfaces.ICoach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class CoachService implements ICoach {
    @Autowired
    public AdminstarteurRepository adminstarteurRepository;
    @Autowired
    public ClubService clubService;
    @Autowired
    AgentAcceuilRepository agentAcceuilRepository;
    @Autowired
    MembreRepository membreRepository;
    @Autowired
    CoachRepository coachRepository;
    @Autowired
    AppWebRepository appWebRepository;
    String getColorButton(String theme){
        if(theme.equals("theme1")){
            return "#00C3F9";
        }
        if(theme.equals("theme2")){
            return "#91969E";
        }
        if(theme.equals("theme3")){
            return "#DB5363";
        }
        if(theme.equals("theme4")){
            return "#EA6A12";
        }
        if(theme.equals("theme5")){
            return "#E586B3";
        }
        if(theme.equals("theme6")){
            return "#079aa2";
        }
        return null;
    }


    @Autowired
    private EmailService emailService;
    @Autowired
    private NotificationService notificationService;
    @Override
    public Coach saveCoach(Coach coach) {
        Club club=coach.getClub();
        AppWeb appWeb=appWebRepository.findByClub(club);
        String buttonColor =appWeb != null ? getColorButton(appWeb.getCouleurAppWeb()) : "#000000";
        coach.setUpdated(false);
        Coach c=coachRepository.save(coach);
        if(coach.isValidation()){
            String subject = "Informations d'authentification pour club";
            String htmlBody = "<html>" +
                    "<head>" +
                    "<style>" +
                    "body { font-family: Arial, sans-serif; margin: 0; padding: 0; }" +
                    ".container { padding: 20px; }" +
                    "h1 { color: #333; }" +
                    "p { font-size: 16px; color: #555; }" +
                    ".logo { width: 50px; margin-bottom: 20px; vertical-align: middle; }" +
                    ".club-name { display: inline-block; vertical-align: middle; margin-left: 10px; font-size: 24px; color: #333; }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='container'>" +
                    "<div>" +
                    "<img src='" + appWeb.getLogoAppWeb() + "' alt='Logo' class='logo'>" +
                    "<h4 class='club-name'>" + coach.getClub().getNomClub() + "</h4>" +
                    "</div>" +
                    "<h1>Bonjour,</h1>" +
                    "<p>Votre Nouveau Information.</p>" +
                    "<p>Username: " + coach.getUsername() + "</p>" +
                    "<p><strong>Mot de passe: " + coach.getPassword() + "</strong></p>" +
                    "<p>Veuillez changer votre mot de passe après la première connexion.</p>" +
                    "<a href='http://localhost:4200/" + appWeb.getAdresseUrl() + "/loginClub' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color:" + buttonColor + "; text-decoration: none; border-radius: 5px;'>Connectez-vous</a>" +
                    "<p>Cordialement,<br>Expert Dev Solutions</p>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            try {
                emailService.sendHtmlMessage(coach.getEmail(), subject, htmlBody);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }else{
            String notificationMessage = "Le compte du coach " + coach.getNom() +"  "+ coach.getPrenom()+" nécessite une validation.";
            notificationService.notifyAdmins(coach.getClub(), notificationMessage,coach,null);
        }

        return c;
    }

    @Override
    public Coach updateCoach(Coach coach) {
        return coachRepository.saveAndFlush(coach);
    }

    @Override
    public boolean deleteCoach(Long id) {
        coachRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Coach> getListCoach() {
        return coachRepository.findAll();
    }

    @Override
    public Coach getCoachByIdCoach(Long id) {
        return coachRepository.findById(id).get();
    }

    @Override
    public List<Coach> getCoachsByNom(String nom) {
        return coachRepository.findByNom(nom);
    }

    @Override
    public List<Coach> getListCoachValidateByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return coachRepository.getAllByClubAndValidation(club,true);
    }
    @Override
    public List<Coach> getListCoachNotValidateByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return coachRepository.getAllByClubAndValidation(club,false);
    }

    @Override
    public boolean AnnulerCompteCoach(Coach coach) {
        Club club=coach.getClub();
        AppWeb appWeb=appWebRepository.findByClub(club);
        String buttonColor =appWeb != null ? getColorButton(appWeb.getCouleurAppWeb()) : "#000000";
        String subject = "échec de création du compte";
        String htmlBody = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 0; padding: 0; }" +
                ".container { padding: 20px; }" +
                "h1 { color: #333; }" +
                "p { font-size: 16px; color: #555; }" +
                ".logo { width: 50px; margin-bottom: 20px; vertical-align: middle; }" +
                ".club-name { display: inline-block; vertical-align: middle; margin-left: 10px; font-size: 24px; color: #333; }" +
                ".button { display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color: " + buttonColor + "; text-decoration: none; border-radius: 5px; margin-top: 20px; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<div>" +
                "<img src='" + appWeb.getLogoAppWeb() + "' alt='Logo' class='logo'>" +
                "<span class='club-name'>" + coach.getClub().getNomClub() + "</span>" +
                "</div>" +
                "<h1>Bonjour,</h1>" +
                "<p>Nous sommes désolés, mais votre compte n'a pas pu être créé.</p>" +
                "<p>Veuillez réessayer ultérieurement.</p>" +
                "<a href='http://localhost:4200/" + appWeb.getAdresseUrl() + "/loginClub' class='button'>Connectez-vous</a>" +
                "<p>Cordialement,<br>Expert Dev Solutions</p>" +
                "</div>" +
                "</body>" +
                "</html>";

        try {
            emailService.sendHtmlMessage(coach.getEmail(), subject, htmlBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        coachRepository.deleteById(coach.getIdUtilisateur());
        return true;
    }
}
