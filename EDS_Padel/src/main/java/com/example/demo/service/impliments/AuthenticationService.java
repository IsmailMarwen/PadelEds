package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.AdminstarteurRepository;
import com.example.demo.persistance.dao.ClubRepository;
import com.example.demo.persistance.dao.CoachRepository;
import com.example.demo.persistance.dao.MembreRepository;
import com.example.demo.persistance.entities.*;
import com.example.demo.persistance.helper.ContactRequest;
import com.example.demo.persistance.helper.JwtUtil;
import com.example.demo.persistance.helper.UpdatePasswordRequest;
import com.example.demo.persistance.helper.UpdatePasswordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.SecureRandom;

@Service
public class AuthenticationService {
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

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private AdminstarteurRepository administrateurRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ClubRepository clubRepository;

    public String authenticateAndGenerateToken(String username, String password, Club club) {
        Membre membre = membreRepository.findByUsernameAndClub(username, club);
        if (membre != null && membre.getPassword().equals(password)) {
            return generateToken(membre);
        }


        Coach coach = coachRepository.findByUsernameAndClub(username, club);
        if (coach != null && coach.getPassword().equals(password)) {
            return generateToken(coach);
        }


        Administrateur administrateur = administrateurRepository.findByUsernameAndClub(username, club);
        if (administrateur != null && administrateur.getPassword().equals(password)) {
            return generateToken(administrateur);
        }

        return null;
    }

    private String generateToken(Utilisateur utilisateur) {
        return jwtUtil.generateToken(utilisateur.getUsername(), utilisateur.getRole(), utilisateur.getIdUtilisateur(),utilisateur.getUpdated());
    }
    private String getColorButton(String theme){
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
    public Utilisateur resetPassword(Utilisateur user) {
        Membre membre = membreRepository.findByEmailAndClub(user.getEmail(), user.getClub());
        if (membre != null) {
            String generatedPassword = generateRandomPassword(8);
            membre.setPassword(generatedPassword);
            membre.setUpdated(false);
            membreRepository.saveAndFlush(membre);
            sendResetPasswordEmail(user, generatedPassword);
            return membre;
        }

        Coach coach = coachRepository.findByEmailAndClub(user.getEmail(), user.getClub());
        if (coach != null) {
            String generatedPassword = generateRandomPassword(8);
            coach.setPassword(generatedPassword);
            coach.setUpdated(false);
            coachRepository.saveAndFlush(coach);
            sendResetPasswordEmail(user, generatedPassword);
            return coach;
        }

        Administrateur administrateur = administrateurRepository.findByEmailAndClub(user.getEmail(), user.getClub());
        if (administrateur != null) {
            String generatedPassword = generateRandomPassword(8);
            administrateur.setPassword(generatedPassword);
            administrateur.setUpdated(false);
            administrateurRepository.saveAndFlush(administrateur);
            sendResetPasswordEmail(user, generatedPassword);
            return administrateur;
        }

        return null;
    }
    private void sendResetPasswordEmail(Utilisateur user, String generatedPassword) {
        Club club = clubRepository.findById(user.getClub().getIdClub()).orElse(null);
        AppWeb appWeb = club != null ? club.getAppWeb() : null;

        // Default values if AppWeb is null
        String buttonColor = appWeb != null ? getColorButton(appWeb.getCouleurAppWeb()) : "#000000";
        String logoUrl = appWeb != null ? appWeb.getLogoAppWeb() : "default-logo-url";
        String adresseUrl = appWeb != null ? appWeb.getAdresseUrl() : "default-address-url";

        String subject = "Reset Password";
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
                "<img src='" + logoUrl + "' alt='Logo' class='logo'>" +
                "<h4 class='club-name'>" + club.getNomClub() + "</h4>" +
                "</div>" +
                "<h1>Bonjour,</h1>" +
                "<p>Votre Nouveau Mot De Passe.</p>" +
                "<p><strong>Mot de passe: " + generatedPassword + "</strong></p>" +
                "<p>Veuillez changer votre mot de passe après la première connexion.</p>" +
                "<a href='http://localhost:4200/" + adresseUrl + "/loginClub' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color:" + buttonColor + "; text-decoration: none; border-radius: 5px;'>Connectez-vous</a>" +
                "<p>Cordialement,<br>Expert Dev Solutions</p>" +
                "</div>" +
                "</body>" +
                "</html>";

        try {
            emailService.sendHtmlMessage(user.getEmail(), subject, htmlBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
public UpdatePasswordResponse updatePassword(UpdatePasswordRequest updatePasswordRequest){
    UpdatePasswordResponse updatePasswordResponse=new UpdatePasswordResponse();

    if(updatePasswordRequest.getRole().equals("membre")){
        membreRepository.updatePasswordMembreByIdClub(updatePasswordRequest.getPassword(),updatePasswordRequest.getIdClub(),updatePasswordRequest.getUserId());
        updatePasswordResponse.setUpdated(true);
        return updatePasswordResponse;
    }
    if(updatePasswordRequest.getRole().equals("coach")){
        coachRepository.updatePasswordCoachByIdClub(updatePasswordRequest.getPassword(),updatePasswordRequest.getIdClub(),updatePasswordRequest.getUserId());
        updatePasswordResponse.setUpdated(true);
        return updatePasswordResponse;
    }
    if(updatePasswordRequest.getRole().equals("admin")){
        administrateurRepository.updatePasswordAdminByIdClub(updatePasswordRequest.getPassword(),updatePasswordRequest.getIdClub(),updatePasswordRequest.getUserId());
        updatePasswordResponse.setUpdated(true);
        return updatePasswordResponse;
    }
    return updatePasswordResponse;

}
public ContactRequest contactClub(ContactRequest contactRequest){
        Club club=clubRepository.findById(contactRequest.getIdClub()).get();
        AppWeb appWeb=club.getAppWeb();
        String logoUrl =appWeb.getLogoAppWeb();
    String subject = contactRequest.getSujet();
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
            "<img src='" + logoUrl + "' alt='Logo' class='logo'>" +
            "<h4 class='club-name'>" + club.getNomClub() + "</h4>" +
            "</div>" +
            "<h1>Bonjour,</h1>" +
            "<p>Un mail envoyé par l'utilisateur " + contactRequest.getNom() + " " + contactRequest.getPrenom() + " qui a un email " + contactRequest.getEmailUser() + "</p>"+
            "<p>"+contactRequest.getDescription()+"</p>" +
            "<p>Cordialement,<br>Expert Dev Solutions</p>" +
            "</div>" +
            "</body>" +
            "</html>";

    try {
        emailService.sendHtmlMessage(club.getEmail(), subject, htmlBody);
    } catch (MessagingException e) {
        e.printStackTrace();
    }
        return contactRequest;
}
}
