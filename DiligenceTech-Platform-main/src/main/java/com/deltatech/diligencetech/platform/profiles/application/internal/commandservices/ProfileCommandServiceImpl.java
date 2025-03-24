package com.deltatech.diligencetech.platform.profiles.application.internal.commandservices;

import com.deltatech.diligencetech.platform.profiles.domain.model.aggregates.Profile;
import com.deltatech.diligencetech.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.deltatech.diligencetech.platform.profiles.domain.services.ProfileCommandService;
import com.deltatech.diligencetech.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Long handle(CreateProfileCommand command) {
        if (profileRepository.existsByUsername(command.username())) {
            throw new IllegalArgumentException("Profile with same username already exists");
        }
        var profile = new Profile(command);
        try {
            profileRepository.save(profile);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while trying to save profile: " + e.getMessage());
        }
        return profile.getId();
    }
}
