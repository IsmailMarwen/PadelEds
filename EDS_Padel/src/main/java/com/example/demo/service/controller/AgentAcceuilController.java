package com.example.demo.service.controller;

import com.example.demo.persistance.entities.Administrateur;
import com.example.demo.persistance.entities.AgentAcceuil;
import com.example.demo.service.interfaces.IAgentAcceuil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/agentAcceuil")
public class AgentAcceuilController {
    private final IAgentAcceuil iAgentAcceuil;
    public AgentAcceuilController(IAgentAcceuil iAgentAcceuil){
        this.iAgentAcceuil=iAgentAcceuil;}
    @PostMapping("/add")
    AgentAcceuil save(@RequestBody AgentAcceuil agentAcceuil) {
        AgentAcceuil m=iAgentAcceuil.saveAgentAcceuil(agentAcceuil);
        return m ;
    }
    @PutMapping("/update")
    AgentAcceuil update(@RequestBody AgentAcceuil agentAcceuil) {

        return iAgentAcceuil.updateAgentAcceuil(agentAcceuil);
    }
    @GetMapping("/getAll")
    List<AgentAcceuil> getAllAgentAcceuils() {

        return iAgentAcceuil.getListAgentAcceuil();
    }

    @GetMapping("/getById/{id}")
    AgentAcceuil getAgentAcceuilById(@PathVariable Long id) {

        return iAgentAcceuil.getAgentAcceuilByIdAgentAcceuil(id);
    }

    @DeleteMapping("/delete/{id}")
    boolean delete(@PathVariable Long id) {
        iAgentAcceuil.deleteAgentAcceuil(id);
        return true;
    }
    @GetMapping("/getAllByClub/{idClub}")
    List<AgentAcceuil> getAllAgentAcceuilByClub(@PathVariable Long idClub) {

        return iAgentAcceuil.getListAgentAcceuilByClub(idClub);
    }
}
