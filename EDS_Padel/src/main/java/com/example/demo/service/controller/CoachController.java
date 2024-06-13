package com.example.demo.service.controller;
import com.example.demo.persistance.entities.Coach;
import com.example.demo.service.interfaces.ICoach;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/coach")
public class CoachController {
    private final ICoach iCoach;
    public CoachController(ICoach iCoach){
        this.iCoach=iCoach;}
    @PostMapping("/add")
    Coach save(@RequestBody Coach coach) {
        Coach c=iCoach.saveCoach(coach);
        return c ;
    }
    @PutMapping("/update")
    Coach update(@RequestBody Coach coach) {

        return iCoach.updateCoach(coach);
    }
    @GetMapping("/getAll")
    List<Coach> getAllCoachs() {

        return iCoach.getListCoach();
    }

    @GetMapping("/getById/{id}")
    Coach getCoachById(@PathVariable Long id) {

        return iCoach.getCoachByIdCoach(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iCoach.deleteCoach(id);
        return true;
    }
}
