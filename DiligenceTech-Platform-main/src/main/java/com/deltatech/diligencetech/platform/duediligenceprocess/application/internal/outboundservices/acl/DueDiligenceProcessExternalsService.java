package com.deltatech.diligencetech.platform.duediligenceprocess.application.internal.outboundservices.acl;
import com.deltatech.diligencetech.platform.duediligencemanagement.interfaces.outboundservices.acl.DueDiligenceManagementContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DueDiligenceProcessExternalsService {

  private final DueDiligenceManagementContextFacade dueDiligenceManagementContextFacade;

  public DueDiligenceProcessExternalsService(DueDiligenceManagementContextFacade projectContextFacade) {
    this.dueDiligenceManagementContextFacade = projectContextFacade;
  }


  /**
   * Fetch agentId by email
   *
   * @param projectId the id to search for
   * @return projectId if found, empty otherwise
   */
  public Optional<Long>  fetchProjectIdById(Long projectId) {
    var testId = dueDiligenceManagementContextFacade.fetchProjectIdById(projectId);
    if (testId == 0L) return Optional.empty();
    return Optional.of(testId);
  }
}