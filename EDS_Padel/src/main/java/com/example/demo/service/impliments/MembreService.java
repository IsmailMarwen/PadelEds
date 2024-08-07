package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.AppWebRepository;
import com.example.demo.persistance.dao.MembreRepository;
import com.example.demo.persistance.entities.AppWeb;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Coach;
import com.example.demo.persistance.entities.Membre;
import com.example.demo.service.interfaces.IMembre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service

public class MembreService implements IMembre {
    @Autowired
    public MembreRepository membreRepository;
    @Autowired
    public ClubService clubService;
    @Autowired
    AppWebRepository appWebRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    NotificationService notificationService;
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
    @Override
    public Membre saveMembre(Membre membre) {
        Club club=membre.getClub();
        AppWeb appWeb=appWebRepository.findByClub(club);
        String buttonColor =appWeb != null ? getColorButton(appWeb.getCouleurAppWeb()) : "#000000";
        membre.setUpdated(false);
        Membre c=membreRepository.save(membre);
        if(membre.isValidation()){
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
                    "<h4 class='club-name'>" + membre.getClub().getNomClub() + "</h4>" +
                    "</div>" +
                    "<h1>Bonjour,</h1>" +
                    "<p>Votre Nouveau Information.</p>" +
                    "<p>Username: " + membre.getUsername() + "</p>" +
                    "<p><strong>Mot de passe: " + membre.getPassword() + "</strong></p>" +
                    "<p>Veuillez changer votre mot de passe après la première connexion.</p>" +
                    "<a href='http://localhost:4200/" + appWeb.getAdresseUrl() + "/loginClub' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color:" + buttonColor + "; text-decoration: none; border-radius: 5px;'>Connectez-vous</a>" +
                    "<p>Cordialement,<br>Expert Dev Solutions</p>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            try {
                emailService.sendHtmlMessage(membre.getEmail(), subject, htmlBody);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }else{
            String notificationMessage = "Le compte du membre " + membre.getNom() +"  "+ membre.getPrenom()+" nécessite une validation.";
            notificationService.notifyAdmins(membre.getClub(), notificationMessage,null,membre);
        }

        return c;
    }

    @Override
    public Membre updateMembre(Membre membre) {
        return membreRepository.saveAndFlush(membre);
    }

    @Override
    public boolean deleteMembre(Long id) {
        membreRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Membre> getListMembre() {
        return membreRepository.findAll();
    }

    @Override
    public Membre getMembreByIdMembre(Long id) {
        return membreRepository.findById(id).get();
    }

    @Override
    public List<Membre> getListMembreValidateByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return membreRepository.getAllByClubAndValidation(club,true);    }

    @Override
    public List<Membre> getListMembreNotValidateByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return membreRepository.getAllByClubAndValidation(club,false);
    }

    @Override
    public boolean AnnulerCompte(Membre membre) {
        Club club=membre.getClub();
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
                "<span class='club-name'>" + membre.getClub().getNomClub() + "</span>" +
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
            emailService.sendHtmlMessage(membre.getEmail(), subject, htmlBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        membreRepository.deleteById(membre.getIdUtilisateur());
        return true;
    }

    @Override
    public List<Membre> getNonParticipatingMembers(String heureDebut, String dateDernierRes, Long idClub) {
        return membreRepository.findNonParticipatingMembers(heureDebut, dateDernierRes, idClub);

    }
    @Override
    public List<Membre> getListMembresEquiv(int niveau){
        return this.membreRepository.findMembersByNiveauPadelRange(niveau);
    }


}
