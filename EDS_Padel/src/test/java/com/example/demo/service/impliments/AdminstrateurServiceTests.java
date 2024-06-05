package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.AdminstarteurRepository;
import com.example.demo.persistance.entities.Adminstrateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AdminstrateurServiceTests {

    @Mock
    private AdminstarteurRepository adminstarteurRepository;

    @InjectMocks
    private AdminstrateurService adminstrateurService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAdminstrateur() {
        Adminstrateur adminstrateur = new Adminstrateur();
        adminstrateur.setNom("John Doe");

        when(adminstarteurRepository.save(any(Adminstrateur.class))).thenReturn(adminstrateur);

        Adminstrateur savedAdminstrateur = adminstrateurService.saveAdminstrateur(adminstrateur);

        assertThat(savedAdminstrateur.getNom()).isEqualTo("John Doe");
        verify(adminstarteurRepository, times(1)).save(adminstrateur);
    }

    @Test
    void testGetListAdminstrateurs() {
        Adminstrateur admin1 = new Adminstrateur();
        admin1.setNom("John Doe");
        Adminstrateur admin2 = new Adminstrateur();
        admin2.setNom("Jane Doe");

        List<Adminstrateur> admins = Arrays.asList(admin1, admin2);
        when(adminstarteurRepository.findAll()).thenReturn(admins);

        List<Adminstrateur> result = adminstrateurService.getListAdminstarteur();

        assertThat(result).hasSize(2);
        verify(adminstarteurRepository, times(1)).findAll();
    }

    @Test
    void testGetAdminstarteurByIdAdminstarteur() {
        Adminstrateur adminstrateur = new Adminstrateur();
        adminstrateur.setIdUtilisateur(1);
        adminstrateur.setNom("John Doe");

        when(adminstarteurRepository.findById(1L)).thenReturn(Optional.of(adminstrateur));

        Adminstrateur result = adminstrateurService.getAdminstarteurByIdAdminstarteur(1L);

        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("John Doe");
        verify(adminstarteurRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateAdminstarteur() {
        Adminstrateur adminstrateur = new Adminstrateur();
        adminstrateur.setIdUtilisateur(1);
        adminstrateur.setNom("John Doe");

        when(adminstarteurRepository.saveAndFlush(any(Adminstrateur.class))).thenReturn(adminstrateur);

        Adminstrateur updatedAdminstrateur = adminstrateurService.updateAdminstarteur(adminstrateur);

        assertThat(updatedAdminstrateur.getNom()).isEqualTo("John Doe");
        verify(adminstarteurRepository, times(1)).saveAndFlush(adminstrateur);
    }

    @Test
    void testDeleteAdminstarteur() {
        Long id = 1L;

        doNothing().when(adminstarteurRepository).deleteById(id);

        boolean isDeleted = adminstrateurService.deleteAdminstarteur(id);

        assertThat(isDeleted).isTrue();
        verify(adminstarteurRepository, times(1)).deleteById(id);
    }
}
