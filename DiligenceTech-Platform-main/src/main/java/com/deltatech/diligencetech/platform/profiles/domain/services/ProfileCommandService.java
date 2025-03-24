package com.deltatech.diligencetech.platform.profiles.domain.services;

import com.deltatech.diligencetech.platform.profiles.domain.model.commands.CreateProfileCommand;

public interface ProfileCommandService {
    Long handle(CreateProfileCommand command);
}
