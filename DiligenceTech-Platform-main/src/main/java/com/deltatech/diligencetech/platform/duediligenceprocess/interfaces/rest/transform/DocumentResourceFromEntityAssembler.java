package com.deltatech.diligencetech.platform.duediligenceprocess.interfaces.rest.transform;

import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.entities.Document;
import com.deltatech.diligencetech.platform.duediligenceprocess.interfaces.rest.resources.DocumentResource;

public class DocumentResourceFromEntityAssembler {
  public static DocumentResource toResourceFromEntity(Document entity) {
    return new DocumentResource(entity.getId(), entity.getFilename(), entity.getFileUrl(), entity.getFolder().getId());
  }
}
