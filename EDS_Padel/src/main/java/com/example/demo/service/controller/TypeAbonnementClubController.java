package com.example.demo.service.controller;

import com.example.demo.persistance.entities.TypeAbonnementClub;
import com.example.demo.service.interfaces.ITypeAbonnementClub;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/typeAbonnementClub")
public class TypeAbonnementClubController {
    private final ITypeAbonnementClub iTypeAbonnementClub;
    public TypeAbonnementClubController(ITypeAbonnementClub iTypeAbonnementClub){
        this.iTypeAbonnementClub=iTypeAbonnementClub;}
    @PostMapping("/add")
    TypeAbonnementClub save(@RequestBody TypeAbonnementClub typeAbonnementClub) {
        TypeAbonnementClub a=iTypeAbonnementClub.saveTypeAbonnementClub(typeAbonnementClub);
        return a ;
    }
    @PutMapping("/update")
    TypeAbonnementClub update(@RequestBody TypeAbonnementClub typeAbonnementClub) {

        return iTypeAbonnementClub.updateTypeAbonnementClub(typeAbonnementClub);
    }
    @GetMapping("/getAll")
    List<TypeAbonnementClub> getAllTypeAbonnementClubs() {

        return iTypeAbonnementClub.getListTypeAbonnementClub();
    }

    @GetMapping("/getById/{id}")
    TypeAbonnementClub getTypeAbonnementClubById(@PathVariable Long id) {

        return iTypeAbonnementClub.getTypeAbonnementClubByIdTypeAbonnementClub(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iTypeAbonnementClub.deleteTypeAbonnementClub(id);
        return true;
    }
    @GetMapping("/getAllByClub/{idClub}")
    List<TypeAbonnementClub> getAllTypeAbonnementClubByClub(@PathVariable Long idClub) {

        return iTypeAbonnementClub.getListTypeAbonnementClubByClub(idClub);
    }
    @GetMapping("/club/{clubId}/coach/{coachId}")
    public List<TypeAbonnementClub> getAllTypeAbonnementByClubAndCoach(@PathVariable Long idClub, @PathVariable Long idCoach) {
        return iTypeAbonnementClub.getListTypeAbonnementClubByClubAndCoach(idClub, idCoach);
    }}
