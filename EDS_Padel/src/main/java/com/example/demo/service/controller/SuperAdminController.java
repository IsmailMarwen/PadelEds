package com.example.demo.service.controller;

import com.example.demo.persistance.dao.SuperAdminRepository;
import com.example.demo.persistance.entities.SuperAdmin;
import com.example.demo.service.interfaces.ISuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/superAdmin")
public class SuperAdminController {
    @Autowired
    public SuperAdminRepository superAdminRepository;
    private final ISuperAdmin iSuperAdmin;
    public SuperAdminController(ISuperAdmin iSuperAdmin){
        this.iSuperAdmin=iSuperAdmin;}
    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody SuperAdmin superAdmin) {
        if(superAdminRepository.findByEmail(superAdmin.getEmail())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà existe");
        }
        if(superAdminRepository.findByTelephone(superAdmin.getTelephone())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Téléphone déjà existe");
        }
        SuperAdmin a=iSuperAdmin.saveSuperAdmin(superAdmin);
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SuperAdmin superAdmin) {
        if(superAdminRepository.findByEmail(superAdmin.getEmail())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà existe");
        }
        if(superAdminRepository.findByTelephone(superAdmin.getTelephone())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Téléphone déjà existe");
        }
        SuperAdmin a=iSuperAdmin.updateSuperAdmin(superAdmin);
        return ResponseEntity.status(HttpStatus.CREATED).body(a);
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
