package com.deltatech.diligencetech.platform.profiles.application.internal.queryservices;

import com.deltatech.diligencetech.platform.profiles.domain.model.aggregates.Profile;
import com.deltatech.diligencetech.platform.profiles.domain.model.queries.GetProfileByAgentIdQuery;
import com.deltatech.diligencetech.platform.profiles.domain.services.ProfileQueryService;
import com.deltatech.diligencetech.platform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    @Override
    public Optional<Profile> handle(GetProfileByAgentIdQuery query) {
        return profileRepository.findByAgentId(query.agentId());
    }
}
