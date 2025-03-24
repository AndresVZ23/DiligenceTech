package com.deltatech.diligencetech.platform.duediligenceprocess.domain.services;

import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.aggregates.Area;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.commands.CreateAreaCommand;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.commands.UpdateAreaNameCommand;

import java.util.Optional;

public interface AreaCommandService {

  Long handle(CreateAreaCommand command);
  Optional<Area> handle(UpdateAreaNameCommand command);

}
