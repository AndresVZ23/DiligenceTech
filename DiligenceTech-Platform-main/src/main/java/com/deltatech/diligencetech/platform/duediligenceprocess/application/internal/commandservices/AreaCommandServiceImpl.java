package com.deltatech.diligencetech.platform.duediligenceprocess.application.internal.commandservices;

import com.deltatech.diligencetech.platform.duediligenceprocess.application.internal.outboundservices.acl.DueDiligenceProcessExternalsService;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.aggregates.Area;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.commands.CreateAreaCommand;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.commands.UpdateAreaNameCommand;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.services.AreaCommandService;
import com.deltatech.diligencetech.platform.duediligenceprocess.infrastructure.persistence.jpa.repositories.AreaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AreaCommandServiceImpl implements AreaCommandService {
  private final AreaRepository areaRepository;
  private final DueDiligenceProcessExternalsService externalProjectService;


  public AreaCommandServiceImpl(AreaRepository areaRepository, DueDiligenceProcessExternalsService externalProjectService) {
    this.areaRepository = areaRepository;
    this.externalProjectService = externalProjectService;
  }

  @Override
  public Long handle(CreateAreaCommand command) {
    if (areaRepository.existsByName(command.name())) {
      if (Objects.equals(command.projectId(), areaRepository.findByName(command.name()).get().getProjectId()))
        throw new IllegalArgumentException("Area with the same name in this project already exists");
    }
    if (command.projectId() != null) {
      var projectId = externalProjectService.fetchProjectIdById(command.projectId());
      if (projectId.isEmpty()) throw new IllegalArgumentException("Project does not exist");
    }
    var area = new Area(command);
    try {
      areaRepository.save(area);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error while saving area: " + e.getMessage());
    }
    return area.getId();
  }

  @Override
  public Optional<Area> handle(UpdateAreaNameCommand command) {
    var result = areaRepository.findById(command.id());
    if (result.isEmpty()) throw new IllegalArgumentException("Area does not exist");
    var areaToUpdate = result.get();
    try {
      var updatedArea = areaRepository.save(areaToUpdate.updateName(command.name()));
      return Optional.of((Area) updatedArea);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error while updating area: " + e.getMessage());
    }
  }



}
