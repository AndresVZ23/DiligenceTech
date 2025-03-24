package com.deltatech.diligencetech.platform.duediligenceprocess.interfaces.rest.transform;

import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.aggregates.Folder;
import com.deltatech.diligencetech.platform.duediligenceprocess.interfaces.rest.resources.FolderResource;

public class FolderResourceFromEntityAssembler {
  public static FolderResource toResourceFromEntity(Folder entity) {
    return new FolderResource(entity.getId(), entity.getParent().getId(), entity.getName());
  }
}
