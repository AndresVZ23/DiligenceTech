package com.deltatech.diligencetech.platform.profiles.interfaces.acl;

import com.deltatech.diligencetech.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.deltatech.diligencetech.platform.profiles.domain.services.ProfileCommandService;
import org.springframework.stereotype.Service;

@Service
public class ProfilesContextFacade {
    private final ProfileCommandService profileCommandService;

    public ProfilesContextFacade(ProfileCommandService profileCommandService) {
        this.profileCommandService = profileCommandService;
    }

    public Long createProfile(String username, Long userId, String email, String firstName, String lastName) {
        var command = new CreateProfileCommand(username, userId, email, firstName, lastName);
        return profileCommandService.handle(command);
    }
}
