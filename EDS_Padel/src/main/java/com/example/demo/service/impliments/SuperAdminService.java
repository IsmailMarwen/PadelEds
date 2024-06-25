package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.SuperAdminRepository;
import com.example.demo.persistance.entities.SuperAdmin;
import com.example.demo.service.interfaces.ISuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SuperAdminService implements ISuperAdmin {
    
        @Autowired
        public SuperAdminRepository superAdminRepository;

        @Override
        public SuperAdmin saveSuperAdmin(SuperAdmin superAdmin) {
            return superAdminRepository.save(superAdmin);
        }

        @Override
        public SuperAdmin updateSuperAdmin(SuperAdmin superAdmin) {
            return superAdminRepository.saveAndFlush(superAdmin);
        }

        @Override
        public boolean deleteSuperAdmin(Long id) {
            superAdminRepository.deleteById(id);
            return true;
        }

        @Override
        public List<SuperAdmin> getListSuperAdmin() {
            return superAdminRepository.findAll();
        }

        @Override
        public SuperAdmin getSuperAdminByIdSuperAdmin(Long id) {
            return superAdminRepository.findById(id).get();
        }
}
