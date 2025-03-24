package com.deltatech.diligencetech.platform.duediligenceprocess.interfaces.rest.transform;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.aggregates.Area;
import com.deltatech.diligencetech.platform.duediligenceprocess.interfaces.rest.resources.AreaResource;

public class AreaResourceFromEntityAssembler {
  public static AreaResource toResourceFromEntity(Area entity) {
    return new AreaResource(entity.getId(), entity.getName());
  }
}
