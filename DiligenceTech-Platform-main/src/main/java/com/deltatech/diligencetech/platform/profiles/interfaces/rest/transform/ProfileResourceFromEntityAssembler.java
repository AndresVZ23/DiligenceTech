package com.deltatech.diligencetech.platform.profiles.interfaces.rest.transform;

import com.deltatech.diligencetech.platform.profiles.domain.model.aggregates.Profile;
import com.deltatech.diligencetech.platform.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile profile) {
        return new ProfileResource(profile.getId(), profile.getUsername(), profile.getEmail(), profile.getName(), profile.getCreatedAt().toString());
    }
}
