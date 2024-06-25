package com.example.demo.service.controller;
import com.example.demo.persistance.entities.Club;
import com.example.demo.persistance.entities.Utilisateur;
import com.example.demo.persistance.helper.*;
import com.example.demo.service.impliments.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        Club club = loginRequest.getClub();
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        String token = authenticationService.authenticateAndGenerateToken(username, password, club);
        System.out.println("******** test");

        if (token != null) {
            LoginResponse loginResponse=new LoginResponse();
            loginResponse.setToken(token);
            return loginResponse;
        } else {
            LoginResponse loginResponse=new LoginResponse();
            loginResponse.setToken("");
            return loginResponse;
        }
    }
    @PutMapping("/resetPassword")
    public Utilisateur resetPassword(@RequestBody Utilisateur user){
        return authenticationService.resetPassword(user);
    }
    @PutMapping("/updatePassword")
    public UpdatePasswordResponse updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest){
        return authenticationService.updatePassword(updatePasswordRequest);
    }
    @PostMapping("/contact")
    public ContactRequest contact(@RequestBody ContactRequest contactRequest){
        return authenticationService.contactClub(contactRequest);
    }
}
