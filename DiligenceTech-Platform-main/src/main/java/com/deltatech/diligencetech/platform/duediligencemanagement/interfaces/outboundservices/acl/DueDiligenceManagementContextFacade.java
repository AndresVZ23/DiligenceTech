package com.deltatech.diligencetech.platform.duediligencemanagement.interfaces.outboundservices.acl;

import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.commands.CreateInactiveProjectCommand;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.queries.GetProjectByIdQuery;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.services.ProjectCommandService;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.services.ProjectQueryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class DueDiligenceManagementContextFacade {

  private final ProjectQueryService projectQueryService;

  public DueDiligenceManagementContextFacade(ProjectQueryService projectQueryService) {
    this.projectQueryService = projectQueryService;
  }

  /**
   * Fetches the project id by code
   *
   * @param projectId the code
   * @return the agent id
   */

  public Long fetchProjectIdById(Long projectId) {
    var getProjectByIdQuery = new GetProjectByIdQuery(projectId);
    var project = projectQueryService.handle(getProjectByIdQuery);
    if (project.isEmpty()) return 0L;
    return project.get().getId();
  }

}