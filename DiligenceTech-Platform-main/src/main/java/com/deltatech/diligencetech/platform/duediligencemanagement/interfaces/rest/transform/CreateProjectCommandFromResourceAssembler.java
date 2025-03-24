package com.deltatech.diligencetech.platform.duediligencemanagement.interfaces.rest.transform;

import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.commands.CreateInactiveProjectCommand;
import com.deltatech.diligencetech.platform.duediligencemanagement.interfaces.rest.resources.CreateProjectResource;

public class CreateProjectCommandFromResourceAssembler {

    public static CreateInactiveProjectCommand toCommandFromResource(CreateProjectResource resource) {
        return new CreateInactiveProjectCommand(resource.name(), resource.agentsUsernames(), resource.agentsRoles());
    }
}