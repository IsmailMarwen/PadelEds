package com.example.demo.persistance.helper;

import com.example.demo.persistance.entities.Club;

public class NotifyRequest {
    private Club club;
    private String message;

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
