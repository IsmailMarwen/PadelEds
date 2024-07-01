package com.example.demo.service.interfaces;

import com.example.demo.persistance.entities.AgentAcceuil;

import java.util.List;

public interface IAgentAcceuil {
    AgentAcceuil saveAgentAcceuil(AgentAcceuil agentAcceuil);
    AgentAcceuil updateAgentAcceuil(AgentAcceuil agentAcceuil);
    boolean deleteAgentAcceuil(Long id);
    List<AgentAcceuil> getListAgentAcceuil();
    AgentAcceuil getAgentAcceuilByIdAgentAcceuil(Long id);
}
