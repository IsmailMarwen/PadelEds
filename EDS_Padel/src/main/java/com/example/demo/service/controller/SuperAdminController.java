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
    if (superAdminRepository.findByEmail(superAdmin.getEmail()) != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà existe");
    }
    if (superAdminRepository.findByTelephone(superAdmin.getTelephone()) != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Téléphone déjà existe");
    }
    SuperAdmin savedSuperAdmin = iSuperAdmin.saveSuperAdmin(superAdmin);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedSuperAdmin);
}

@PutMapping("/update")
public ResponseEntity<?> update(@RequestBody SuperAdmin superAdmin) {
    SuperAdmin existingSuperAdmin = superAdminRepository.findById(superAdmin.getId()).orElse(null);
    
    if (existingSuperAdmin == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SuperAdmin non trouvé");
    }

    SuperAdmin superAdminWithSameEmail = superAdminRepository.findByEmail(superAdmin.getEmail());
    if (superAdminWithSameEmail != null && !superAdminWithSameEmail.getId().equals(superAdmin.getId())) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email déjà existe");
    }

    SuperAdmin superAdminWithSameTelephone = superAdminRepository.findByTelephone(superAdmin.getTelephone());
    if (superAdminWithSameTelephone != null && !superAdminWithSameTelephone.getId().equals(superAdmin.getId())) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Téléphone déjà existe");
    }

    SuperAdmin updatedSuperAdmin = iSuperAdmin.updateSuperAdmin(superAdmin);
    return ResponseEntity.status(HttpStatus.OK).body(updatedSuperAdmin);
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
