package com.deltatech.diligencetech.platform.profiles.domain.services;

import com.deltatech.diligencetech.platform.profiles.domain.model.aggregates.Profile;
import com.deltatech.diligencetech.platform.profiles.domain.model.queries.GetProfileByAgentIdQuery;

import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByAgentIdQuery query);
}
