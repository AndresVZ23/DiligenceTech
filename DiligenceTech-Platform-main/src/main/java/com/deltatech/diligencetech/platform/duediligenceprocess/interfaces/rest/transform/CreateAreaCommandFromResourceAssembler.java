package com.deltatech.diligencetech.platform.duediligenceprocess.interfaces.rest.transform;

import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.commands.CreateAreaCommand;
import com.deltatech.diligencetech.platform.duediligenceprocess.interfaces.rest.resources.CreateAreaResource;

public class CreateAreaCommandFromResourceAssembler {
  public static CreateAreaCommand toCommandFromResource(CreateAreaResource resource) {
    return new CreateAreaCommand(resource.projectId(), resource.name());
  }
}
