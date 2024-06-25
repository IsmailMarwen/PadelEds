package com.example.demo.persistance.helper;

public class UpdatePasswordRequest {
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getIdClub() {
        return idClub;
    }

    public void setIdClub(Long idClub) {
        this.idClub = idClub;
    }

    private Long userId;
    private String role;
    private Long idClub;

    public String getPassword() {
        return password;
    }

    private String password;
}
