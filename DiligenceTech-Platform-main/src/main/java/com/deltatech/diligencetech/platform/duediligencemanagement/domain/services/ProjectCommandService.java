package com.deltatech.diligencetech.platform.duediligencemanagement.domain.services;

import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.aggregates.Project;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.commands.*;

import java.util.Optional;

public interface ProjectCommandService {
    Optional<Long> handle(CreateInactiveProjectCommand command);
    void handle(DeactivateProjectCommand command);
    void handle(ActivateProjectCommand command);
}
