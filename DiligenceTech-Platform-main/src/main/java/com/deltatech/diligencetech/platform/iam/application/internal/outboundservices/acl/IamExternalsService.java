package com.deltatech.diligencetech.platform.iam.application.internal.outboundservices.acl;

import com.deltatech.diligencetech.platform.iam.domain.model.commands.SignUpCommand;
import com.deltatech.diligencetech.platform.iam.domain.model.valueobjects.AgentId;
import com.deltatech.diligencetech.platform.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IamExternalsService {
    private final ProfilesContextFacade profilesContextFacade;

    public IamExternalsService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public Optional<AgentId> createProfileByUsername(SignUpCommand command, Long userId) {
        var agentId = profilesContextFacade.createProfile(
                command.username(),
                userId,
                command.email(),
                command.firstName(),
                command.lastName()
        );
        if (agentId == 0L) return Optional.empty();
        return Optional.of(new AgentId(agentId));
    }
}
