package com.deltatech.diligencetech.platform.duediligencecommunication.application.internal.outboundservices.acl;

import com.deltatech.diligencetech.platform.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommunicationExternalsService {

    private final IamContextFacade iamContextFacade;

    public CommunicationExternalsService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public Optional<Long>  fetchAgentIdByUsername(String username) {
        var agentId = iamContextFacade.fetchUserByUsername(username);
        return agentId;
    }
}
