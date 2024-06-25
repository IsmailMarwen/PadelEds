package com.example.demo.persistance.helper;

import com.example.demo.persistance.entities.SuperAdmin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginSuperAdminResponse {
    private String token;

    private SuperAdmin superAdmin;
}
