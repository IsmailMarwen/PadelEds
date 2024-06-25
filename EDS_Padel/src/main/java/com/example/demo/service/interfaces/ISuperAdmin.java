package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.SuperAdmin;

import java.util.List;

public interface ISuperAdmin {
    SuperAdmin saveSuperAdmin(SuperAdmin superAdmin);
    SuperAdmin updateSuperAdmin(SuperAdmin superAdmin);
    boolean deleteSuperAdmin(Long id);
    List<SuperAdmin> getListSuperAdmin();
    SuperAdmin getSuperAdminByIdSuperAdmin(Long id);
}
