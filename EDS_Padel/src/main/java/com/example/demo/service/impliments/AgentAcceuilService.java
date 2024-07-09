package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.AdminstarteurRepository;
import com.example.demo.persistance.dao.AgentAcceuilRepository;
import com.example.demo.persistance.dao.CoachRepository;
import com.example.demo.persistance.dao.MembreRepository;
import com.example.demo.persistance.entities.AgentAcceuil;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Coach;
import com.example.demo.persistance.entities.Membre;
import com.example.demo.service.interfaces.IAgentAcceuil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.SecureRandom;
import java.util.List;

@Service

public class AgentAcceuilService implements IAgentAcceuil {
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
    @Override
    public AgentAcceuil saveAgentAcceuil(AgentAcceuil a) {
        if(adminstarteurRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || agentAcceuilRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || membreRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || coachRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null){
            return null;
        }
        if(adminstarteurRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || agentAcceuilRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || membreRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || coachRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null){
            return null;
        }
        if(adminstarteurRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || agentAcceuilRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || membreRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || coachRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null){
            return null;
        }
        String buttonColor = a.getClub().getAppWeb() != null ? getColorButton(a.getClub().getAppWeb().getCouleurAppWeb()) : "#000000";
        a.setUpdated(false);
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
                "<img src='" + a.getClub().getAppWeb().getLogoAppWeb() + "' alt='Logo' class='logo'>" +
                "<h4 class='club-name'>" + a.getClub().getNomClub() + "</h4>" +
                "</div>" +
                "<h1>Bonjour,</h1>" +
                "<p>Votre Nouveau Information.</p>" +
                "<p>Username: " + a.getUsername() + "</p>" +
                "<p><strong>Mot de passe: " + a.getPassword() + "</strong></p>" +
                "<p>Veuillez changer votre mot de passe après la première connexion.</p>" +
                "<a href='http://localhost:4200/" + a.getClub().getAppWeb().getAdresseUrl() + "/loginClub' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color:" + buttonColor + "; text-decoration: none; border-radius: 5px;'>Connectez-vous</a>" +
                "<p>Cordialement,<br>Expert Dev Solutions</p>" +
                "</div>" +
                "</body>" +
                "</html>";

        try {
            emailService.sendHtmlMessage(a.getEmail(), subject, htmlBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return agentAcceuilRepository.save(a);
    }

    @Override
    public AgentAcceuil updateAgentAcceuil(AgentAcceuil a) {
        if(adminstarteurRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || agentAcceuilRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || membreRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || coachRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null){
            return null;
        }
        if(adminstarteurRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || agentAcceuilRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || membreRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || coachRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null){
            return null;
        }
        if(adminstarteurRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || agentAcceuilRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || membreRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || coachRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null){
            return null;
        }
        String buttonColor = a.getClub().getAppWeb() != null ? getColorButton(a.getClub().getAppWeb().getCouleurAppWeb()) : "#000000";
        AgentAcceuil aG=agentAcceuilRepository.findById(a.getIdUtilisateur()).get();
        if(!a.getUsername().equals(aG.getUsername())||!a.getPassword().equals(aG.getPassword())){
        a.setUpdated(false);
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
                "<img src='" + a.getClub().getAppWeb().getLogoAppWeb() + "' alt='Logo' class='logo'>" +
                "<h4 class='club-name'>" + a.getClub().getNomClub() + "</h4>" +
                "</div>" +
                "<h1>Bonjour,</h1>" +
                "<p>Votre Nouveau Information.</p>" +
                "<p>Username: " + a.getUsername() + "</p>" +
                "<p><strong>Mot de passe: " + a.getPassword() + "</strong></p>" +
                "<p>Veuillez changer votre mot de passe après la première connexion.</p>" +
                "<a href='http://localhost:4200/" + a.getClub().getAppWeb().getAdresseUrl() + "/loginClub' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color:" + buttonColor + "; text-decoration: none; border-radius: 5px;'>Connectez-vous</a>" +
                "<p>Cordialement,<br>Expert Dev Solutions</p>" +
                "</div>" +
                "</body>" +
                "</html>";

        try {
            emailService.sendHtmlMessage(a.getEmail(), subject, htmlBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }}
        return agentAcceuilRepository.saveAndFlush(a);
    }

    @Override
    public boolean deleteAgentAcceuil(Long id) {
        agentAcceuilRepository.deleteById(id);
        return true;
    }

    @Override
    public List<AgentAcceuil> getListAgentAcceuil() {
        return agentAcceuilRepository.findAll();
    }

    @Override
    public AgentAcceuil getAgentAcceuilByIdAgentAcceuil(Long id) {
        return agentAcceuilRepository.findById(id).get();
    }

    @Override
    public List<AgentAcceuil> getListAgentAcceuilByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return agentAcceuilRepository.getAllByClub(club);
    }
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    public static String generateRandomPassword(int length) {
        if (length < 1) throw new IllegalArgumentException("Length must be greater than 0");

        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }
    public Coach ValidateCompteCoach(Coach coach){
        String buttonColor = coach.getClub().getAppWeb() != null ? getColorButton(coach.getClub().getAppWeb().getCouleurAppWeb()) : "#000000";
        String generatedPassword = generateRandomPassword(8);
        coach.setPassword(generatedPassword);
        coach.setUsername(coach.getNom() + generateRandomPassword(2));
        coach.setValidation(true);
        coach.setUpdated(false);
        String subject = "Validation du compte";
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
                "<img src='" + coach.getClub().getAppWeb().getLogoAppWeb() + "' alt='Logo' class='logo'>" +
                "<h4 class='club-name'>" + coach.getClub().getNomClub() + "</h4>" +
                "</div>" +
                "<h1>Bonjour,</h1>" +
                "<p>Votre Information d'authentification.</p>" +
                "<p>Username: " + coach.getUsername() + "</p>" +
                "<p><strong>Mot de passe: " + coach.getPassword() + "</strong></p>" +
                "<p>Veuillez changer votre mot de passe après la première connexion.</p>" +
                "<a href='http://localhost:4200/" + coach.getClub().getAppWeb().getAdresseUrl() + "/loginClub' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color:" + buttonColor + "; text-decoration: none; border-radius: 5px;'>Connectez-vous</a>" +
                "<p>Cordialement,<br>Expert Dev Solutions</p>" +
                "</div>" +
                "</body>" +
                "</html>";

        try {
            emailService.sendHtmlMessage(coach.getEmail(), subject, htmlBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return coachRepository.saveAndFlush(coach);
    }

    public Membre ValidateCompteMembre(Membre membre){
        String buttonColor = membre.getClub().getAppWeb() != null ? getColorButton(membre.getClub().getAppWeb().getCouleurAppWeb()) : "#000000";
        String generatedPassword = generateRandomPassword(8);
        membre.setPassword(generatedPassword);
        membre.setUsername(membre.getNom() + generateRandomPassword(2));
        membre.setValidation(true);
        membre.setUpdated(false);
        String subject = "Validation du compte";
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
                "<img src='" + membre.getClub().getAppWeb().getLogoAppWeb() + "' alt='Logo' class='logo'>" +
                "<h4 class='club-name'>" + membre.getClub().getNomClub() + "</h4>" +
                "</div>" +
                "<h1>Bonjour,</h1>" +
                "<p>Votre Information d'authentification.</p>" +
                "<p>Username: " + membre.getUsername() + "</p>" +
                "<p><strong>Mot de passe: " + membre.getPassword() + "</strong></p>" +
                "<p>Veuillez changer votre mot de passe après la première connexion.</p>" +
                "<a href='http://localhost:4200/" + membre.getClub().getAppWeb().getAdresseUrl() + "/loginClub' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color:" + buttonColor + "; text-decoration: none; border-radius: 5px;'>Connectez-vous</a>" +
                "<p>Cordialement,<br>Expert Dev Solutions</p>" +
                "</div>" +
                "</body>" +
                "</html>";

        try {
            emailService.sendHtmlMessage(membre.getEmail(), subject, htmlBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return membreRepository.saveAndFlush(membre);
    }

}
