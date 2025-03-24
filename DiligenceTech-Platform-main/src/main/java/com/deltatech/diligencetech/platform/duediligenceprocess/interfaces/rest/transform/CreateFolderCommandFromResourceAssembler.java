package com.deltatech.diligencetech.platform.duediligenceprocess.interfaces.rest.transform;

import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.commands.CreateFolderCommand;
import com.deltatech.diligencetech.platform.duediligenceprocess.interfaces.rest.resources.CreateFolderResource;

public class CreateFolderCommandFromResourceAssembler {
  public static CreateFolderCommand toCommandFromResource(CreateFolderResource resource) {
    return new CreateFolderCommand(resource.areaId(), resource.name());
  }
}
