package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.*;
import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.Club;
import com.example.demo.service.interfaces.IAdministrateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.SecureRandom;
import java.util.List;

@Service
public class AdministrateurService implements IAdministrateur {
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
    private EmailService emailService;
    @Override
    public Administrateur saveAdminstrateur(Administrateur a) {
        if(adminstarteurRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || agentAcceuilRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || membreRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null || coachRepository.findByEmailAndClub(a.getEmail(),a.getClub())!=null){
            return null;
        }
        if(adminstarteurRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || agentAcceuilRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || membreRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null || coachRepository.findByTelephoneAndClub(a.getTelephone(),a.getClub())!=null){
            return null;
        }
        if(adminstarteurRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || agentAcceuilRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || membreRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null || coachRepository.findByUsernameAndClub(a.getUsername(),a.getClub())!=null){
            return null;
        }
        a.setUpdated(false);
        String subject = "Informations d'authentification pour club";
        String htmlBody = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 0; padding: 0; }" +
                ".container { padding: 20px; }" +
                "h1 { color: #333; }" +
                "p { font-size: 16px; color: #555; }" +
                ".logo { width: 50px; margin-bottom: 20px; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<img src='https://scontent.ftun14-1.fna.fbcdn.net/v/t39.30808-6/291888548_441109058022997_1842427483752470346_n.png?_nc_cat=110&ccb=1-7&_nc_sid=5f2048&_nc_ohc=9sCyIIEYrhsQ7kNvgEi0sN5&_nc_ht=scontent.ftun14-1.fna&oh=00_AYDMRgL_D9JXFw_K98l4nzC7mJwGellHAUhm9gCQUWSW-A&oe=667704EF' alt='Logo' class='logo'>" +
                "<h1>Bonjour,</h1>" +
                "<p>Inforamtion d'authentification.</p>" +
                "<p><strong>Username : " + a.getUsername() + "</strong></p>" +
                "<p><strong>Mot de passe : " + a.getPassword() + "</strong></p>" +
                "<p>Veuillez changer votre mot de passe après la première connexion.</p>" +
                "<a href='http://localhost:4200/" + a.getClub().getAppWeb().getAdresseUrl() + "/loginClub' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffff; background-color: #fcbc04; text-decoration: none; border-radius: 5px;'>Connectez-vous</a>" +
                "<p>Cordialement,<br>Expert Dev Solutions</p>" +
                "</div>" +
                "</body>" +
                "</html>";

        try {
            emailService.sendHtmlMessage(a.getEmail(), subject, htmlBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return adminstarteurRepository.save(a);
    }

    @Override
    public Administrateur updateAdminstarteur(Administrateur administrateur) {
        String buttonColor = administrateur.getClub().getAppWeb() != null ? getColorButton(administrateur.getClub().getAppWeb().getCouleurAppWeb()) : "#000000";

        Administrateur admin=adminstarteurRepository.findById(administrateur.getIdUtilisateur()).get();
        if(!administrateur.getUsername().equals(admin.getUsername())||!administrateur.getPassword().equals(admin.getPassword())){
            administrateur.setUpdated(false);
            String subject = "Informations de Modification de profile";
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
                    "<img src='" + administrateur.getClub().getAppWeb().getLogoAppWeb() + "' alt='Logo' class='logo'>" +
                    "<h4 class='club-name'>" + administrateur.getClub().getNomClub() + "</h4>" +
                    "</div>" +
                    "<h1>Bonjour,</h1>" +
                    "<p>Votre Nouveau Information.</p>" +
                    "<p>Username: " + administrateur.getUsername() + "</p>" +
                    "<p><strong>Mot de passe: " + administrateur.getPassword() + "</strong></p>" +
                    "<p>Veuillez changer votre mot de passe après la première connexion.</p>" +
                    "<a href='http://localhost:4200/" + administrateur.getClub().getAppWeb().getAdresseUrl() + "/loginClub' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color:" + buttonColor + "; text-decoration: none; border-radius: 5px;'>Connectez-vous</a>" +
                    "<p>Cordialement,<br>Expert Dev Solutions</p>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            try {
                emailService.sendHtmlMessage(administrateur.getEmail(), subject, htmlBody);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return adminstarteurRepository.saveAndFlush(administrateur);
    }

    @Override
    public boolean deleteAdminstarteur(Long id) {
        adminstarteurRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Administrateur> getListAdminstarteur() {
        return adminstarteurRepository.findAll();
    }

    @Override
    public Administrateur getAdminstarteurByIdAdminstarteur(Long id) {
        return adminstarteurRepository.findById(id).get();
    }

    @Override
    public List<Administrateur> getListAdminstarteurByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return  adminstarteurRepository.getAllByClub(club);
    }


}
