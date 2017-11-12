package com.nsd.diamondcard.DBLayerControllers;

import com.nsd.diamondcard.Model.Buyer;
import com.nsd.diamondcard.Model.ContrAgent;

import java.util.List;

public interface DBContrAgent {

    void createContrAgent(ContrAgent ContrAgent);
    void updateContrAgent(ContrAgent ContrAgent);
    void removeContrAgent(ContrAgent ContrAgent);
    ContrAgent getContrAgent(String login);
    ContrAgent getContrAgentWithForeign(long foreignId);
    ContrAgent getContrAgent(long id);
    List<ContrAgent> getAllContrAgents();
    void validateContrAgent(ContrAgent ContrAgent);
}
