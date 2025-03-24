package com.deltatech.diligencetech.platform.duediligencemanagement.application.internal.outboundservices.acl;


import com.deltatech.diligencetech.platform.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DueDiligenceManagementExternalsService {

  private final IamContextFacade iamContextFacade;

  public DueDiligenceManagementExternalsService(IamContextFacade iamContextFacade) {
    this.iamContextFacade = iamContextFacade;
  }

  public List<Long> fetchAgentsIdByUsernames(List<String> usernames) {
    return iamContextFacade.fetchUsersIdByUsernames(usernames);
  }

  public Optional<Long> fetchUserIdByUsername(String username) {
    return iamContextFacade.fetchUserByUsername(username);
  }
}
