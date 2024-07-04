package com.example.demo.service.impliments;

import com.example.demo.persistance.dao.AgentAcceuilRepository;
import com.example.demo.persistance.entities.AgentAcceuil;
import com.example.demo.persistance.entities.Club;
import com.example.demo.service.interfaces.IAgentAcceuil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AgentAcceuilService implements IAgentAcceuil {
    @Autowired
    public AgentAcceuilRepository agentAcceuilRepository;
    @Autowired
    public ClubService clubService;
    @Override
    public AgentAcceuil saveAgentAcceuil(AgentAcceuil agentAcceuil) {
        return agentAcceuilRepository.save(agentAcceuil);
    }

    @Override
    public AgentAcceuil updateAgentAcceuil(AgentAcceuil agentAcceuil) {
        return agentAcceuilRepository.saveAndFlush(agentAcceuil);
    }

    @Override
    public boolean deleteAgentAcceuil(Long id) {
        agentAcceuilRepository.deleteById(id);
        return true;
    }

    @Override
    public List<AgentAcceuil> getListAgentAcceuil() {
        return agentAcceuilRepository.findAll();
    }

    @Override
    public AgentAcceuil getAgentAcceuilByIdAgentAcceuil(Long id) {
        return agentAcceuilRepository.findById(id).get();
    }

    @Override
    public List<AgentAcceuil> getListAgentAcceuilByClub(Long idClub) {
        Club club=clubService.getClubByIdClub(idClub);
        return agentAcceuilRepository.getAllByClub(club);
    }
}
