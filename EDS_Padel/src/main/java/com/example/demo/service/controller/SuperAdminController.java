package com.example.demo.service.controller;

import com.example.demo.persistance.entities.SuperAdmin;
import com.example.demo.service.interfaces.ISuperAdmin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/superAdmin")
public class SuperAdminController {
    private final ISuperAdmin iSuperAdmin;
    public SuperAdminController(ISuperAdmin iSuperAdmin){
        this.iSuperAdmin=iSuperAdmin;}
    @PostMapping("/add")
    SuperAdmin save(@RequestBody SuperAdmin superAdmin) {
        SuperAdmin a=iSuperAdmin.saveSuperAdmin(superAdmin);
        return a ;
    }
    @PutMapping("/update")
    SuperAdmin update(@RequestBody SuperAdmin superAdmin) {

        return iSuperAdmin.updateSuperAdmin(superAdmin);
    }
    @GetMapping("/getAll")
    List<SuperAdmin> getAllSuperAdmins() {

        return iSuperAdmin.getListSuperAdmin();
    }

    @GetMapping("/getById/{id}")
    SuperAdmin getAdminstarteurnById(@PathVariable Long id) {

        return iSuperAdmin.getSuperAdminByIdSuperAdmin(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iSuperAdmin.deleteSuperAdmin(id);
        return true;
    }
}
