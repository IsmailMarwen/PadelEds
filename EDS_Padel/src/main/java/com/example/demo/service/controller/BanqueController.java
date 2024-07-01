package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Banque;
import com.example.demo.service.interfaces.IBanque;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/banque")
public class BanqueController {
    private final IBanque iBanque;
    public BanqueController(IBanque iBanque){
        this.iBanque=iBanque;}
    @PostMapping("/add")
    Banque save(@RequestBody Banque banque) {
        Banque a=iBanque.saveBanque(banque);
        return a ;
    }
    @PutMapping("/update")
    Banque update(@RequestBody Banque banque) {

        return iBanque.updateBanque(banque);
    }
    @GetMapping("/getAll")
    List<Banque> getAllBanques() {

        return iBanque.getListBanque();
    }

    @GetMapping("/getById/{id}")
    Banque getBanqueById(@PathVariable Long id) {

        return iBanque.getBanqueByIdBanque(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iBanque.deleteBanque(id);
        return true;
    }
}
