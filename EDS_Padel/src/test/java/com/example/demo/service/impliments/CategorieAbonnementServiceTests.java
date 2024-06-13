package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.CategorieAbonnementRepository;
import com.example.demo.persistance.entities.CategorieAbonnement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategorieAbonnementServiceTests {
    @Mock
    private CategorieAbonnementRepository categorieAbonnementRepository;

    @InjectMocks
    private CategorieAbonnementService categorieAbonnementService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategorieAbonnement() {
        CategorieAbonnement categorieAbonnement = new CategorieAbonnement();
        categorieAbonnement.setDesignation("CategorieAbonnement n1");

        when(categorieAbonnementRepository.save(any(CategorieAbonnement.class))).thenReturn(categorieAbonnement);

        CategorieAbonnement savedCategorieAbonnement = categorieAbonnementService.saveCategorieAbonnement(categorieAbonnement);

        assertThat(savedCategorieAbonnement.getDesignation()).isEqualTo("CategorieAbonnement n1");
        verify(categorieAbonnementRepository, times(1)).save(categorieAbonnement);
    }

    @Test
    void testGetListCategorieAbonnements() {
        CategorieAbonnement admin1 = new CategorieAbonnement();
        admin1.setDesignation("CategorieAbonnement1");
        CategorieAbonnement admin2 = new CategorieAbonnement();
        admin2.setDesignation("CategorieAbonnement2");

        List<CategorieAbonnement> admins = Arrays.asList(admin1, admin2);
        when(categorieAbonnementRepository.findAll()).thenReturn(admins);

        List<CategorieAbonnement> result = categorieAbonnementService.getListCategorieAbonnement();

        assertThat(result).hasSize(2);
        verify(categorieAbonnementRepository, times(1)).findAll();
    }
    @Test
    void testGetCategorieAbonnementByIdCategorieAbonnement() {
        CategorieAbonnement categorieAbonnement = new CategorieAbonnement();
        categorieAbonnement.setIdCategorie(1L);
        categorieAbonnement.setDesignation("CategorieAbonnement1");

        when(categorieAbonnementRepository.findById(1L)).thenReturn(Optional.of(categorieAbonnement));

        CategorieAbonnement result = categorieAbonnementService.getCategorieAbonnementByIdCategorieAbonnement(1L);

        assertThat(result).isNotNull();
        assertThat(result.getDesignation()).isEqualTo("CategorieAbonnement1");
        verify(categorieAbonnementRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateCategorieAbonnement() {
        CategorieAbonnement categorieAbonnement = new CategorieAbonnement();
        categorieAbonnement.setIdCategorie(1L);
        categorieAbonnement.setDesignation("CategorieAbonnement1");

        when(categorieAbonnementRepository.saveAndFlush(any(CategorieAbonnement.class))).thenReturn(categorieAbonnement);

        CategorieAbonnement updatedCategorieAbonnement = categorieAbonnementService.updateCategorieAbonnement(categorieAbonnement);

        assertThat(updatedCategorieAbonnement.getDesignation()).isEqualTo("CategorieAbonnement1");
        verify(categorieAbonnementRepository, times(1)).saveAndFlush(categorieAbonnement);
    }

    @Test
    void testDeleteCategorieAbonnement() {
        Long id = 1L;

        doNothing().when(categorieAbonnementRepository).deleteById(id);

        boolean isDeleted = categorieAbonnementService.deleteCategorieAbonnement(id);

        assertThat(isDeleted).isTrue();
        verify(categorieAbonnementRepository, times(1)).deleteById(id);
    }
}
