package com.example.demo.persistance.helper;

import com.example.demo.persistance.entities.AppWeb;
import com.example.demo.persistance.entities.Club;

public class ClubAppWebRequest {
    private Club club;
    private AppWeb appWeb;

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public AppWeb getAppWeb() {
        return appWeb;
    }

    public void setAppWeb(AppWeb appWeb) {
        this.appWeb = appWeb;
    }
}
