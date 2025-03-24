package com.deltatech.diligencetech.platform.duediligenceprocess.interfaces.rest.transform;

import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.commands.CreateDocumentCommand;
import com.deltatech.diligencetech.platform.duediligenceprocess.interfaces.rest.resources.CreateDocumentResource;

public class CreateDocumentCommandFromResourceAssembler {
  public static CreateDocumentCommand toCommandFromResource(CreateDocumentResource resource) {
    return new CreateDocumentCommand(resource.folderId(), resource.filename(), resource.fileUrl());
  }
}
