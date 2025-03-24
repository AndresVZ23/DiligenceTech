package com.deltatech.diligencetech.platform.duediligenceprocess.interfaces.rest.transform;

import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.commands.UpdateAreaNameCommand;

public class UpdateAreaNameCommandFromResourceAssembler {
  public static UpdateAreaNameCommand toCommandFromResource(Long areaId, String name) {
    return new UpdateAreaNameCommand(areaId, name);
  }
}
