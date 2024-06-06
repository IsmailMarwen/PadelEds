package com.example.demo.service.impliments;
import com.example.demo.persistance.dao.CoachRepository;
import com.example.demo.persistance.entities.Coach;
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

public class CoachServiceTests {
    @Mock
    private CoachRepository coachRepository;

    @InjectMocks
    private CoachService coachService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSaveCoach() {
        Coach coach = new Coach();
        coach.setNom("test");
        when(coachRepository.save(any(Coach.class))).thenReturn(coach);
        Coach savedCoach = coachRepository.save(coach);

        assertThat(savedCoach.getNom()).isEqualTo("test");
        verify(coachRepository, times(1)).save(coach);
    }
    @Test
    void testGetListCoach() {
        Coach coach1 = new Coach();
        coach1.setNom("test1");
        Coach coach2 = new Coach();
        coach2.setNom("test2");

        List<Coach> coachs = Arrays.asList(coach1, coach2);
        when(coachRepository.findAll()).thenReturn(coachs);

        List<Coach> result = coachService.getListCoach();

        assertThat(result).hasSize(2);
        verify(coachRepository, times(1)).findAll();
    }
    @Test
    void testGetCoachByIdCoach() {
        Coach coach = new Coach();
        coach.setIdUtilisateur(1);
        coach.setNom("Coach");
        when(coachRepository.findById(1L)).thenReturn(Optional.of(coach));
        Coach result = coachService.getCoachByIdCoach(1L);
        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("Coach");
        verify(coachRepository, times(1)).findById(1L);
    }
    @Test
    void testUpdateCoach() {
        Coach coach = new Coach();
        coach.setIdUtilisateur(1);
        coach.setNom("Coach");

        when(coachRepository.saveAndFlush(any(Coach.class))).thenReturn(coach);

        Coach updateCoach = coachService.updateCoach(coach);

        assertThat(updateCoach.getNom()).isEqualTo("Coach");
        verify(coachRepository, times(1)).saveAndFlush(coach);
    }

    @Test
    void testDeleteCoach() {
        Long id = 1L;

        doNothing().when(coachRepository).deleteById(id);

        boolean isDeleted = coachService.deleteCoach(id);

        assertThat(isDeleted).isTrue();
        verify(coachRepository, times(1)).deleteById(id);
    }
}
