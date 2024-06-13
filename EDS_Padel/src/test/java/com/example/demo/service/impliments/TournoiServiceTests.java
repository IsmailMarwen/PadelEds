package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.TournoiRepository;
import com.example.demo.persistance.entities.Tournoi;
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

public class TournoiServiceTests {
    @Mock
    private TournoiRepository tournoiRepository;

    @InjectMocks
    private TournoiService tournoiService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTournoi() {
        Tournoi Tournoi = new Tournoi();
        Tournoi.setNomTournoi("tournoi n1");

        when(tournoiRepository.save(any(Tournoi.class))).thenReturn(Tournoi);

        Tournoi savedTournoi = tournoiService.saveTournoi(Tournoi);

        assertThat(savedTournoi.getNomTournoi()).isEqualTo("tournoi n1");
        verify(tournoiRepository, times(1)).save(Tournoi);
    }

    @Test
    void testGetListTournois() {
        Tournoi admin1 = new Tournoi();
        admin1.setNomTournoi("Tournoi1");
        Tournoi admin2 = new Tournoi();
        admin2.setNomTournoi("Tournoi2");

        List<Tournoi> admins = Arrays.asList(admin1, admin2);
        when(tournoiRepository.findAll()).thenReturn(admins);

        List<Tournoi> result = tournoiService.getListTournoi();

        assertThat(result).hasSize(2);
        verify(tournoiRepository, times(1)).findAll();
    }
    @Test
    void testGetTournoiByIdTournoi() {
        Tournoi tournoi = new Tournoi();
        tournoi.setIdTournoi(1L);
        tournoi.setNomTournoi("Tournoi1");

        when(tournoiRepository.findById(1L)).thenReturn(Optional.of(tournoi));

        Tournoi result = tournoiService.getTournoiByIdTournoi(1L);

        assertThat(result).isNotNull();
        assertThat(result.getNomTournoi()).isEqualTo("Tournoi1");
        verify(tournoiRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateTournoi() {
        Tournoi Tournoi = new Tournoi();
        Tournoi.setIdTournoi(1L);
        Tournoi.setNomTournoi("Tournoi1");

        when(tournoiRepository.saveAndFlush(any(Tournoi.class))).thenReturn(Tournoi);

        Tournoi updatedTournoi = tournoiService.updateTournoi(Tournoi);

        assertThat(updatedTournoi.getNomTournoi()).isEqualTo("Tournoi1");
        verify(tournoiRepository, times(1)).saveAndFlush(Tournoi);
    }

    @Test
    void testDeleteTournoi() {
        Long id = 1L;

        doNothing().when(tournoiRepository).deleteById(id);

        boolean isDeleted = tournoiService.deleteTournoi(id);

        assertThat(isDeleted).isTrue();
        verify(tournoiRepository, times(1)).deleteById(id);
    }
}
