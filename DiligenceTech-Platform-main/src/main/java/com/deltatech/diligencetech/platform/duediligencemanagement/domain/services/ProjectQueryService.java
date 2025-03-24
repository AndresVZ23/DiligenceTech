package com.deltatech.diligencetech.platform.duediligencemanagement.domain.services;

import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.aggregates.Project;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.entities.Member;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProjectQueryService {
    Optional<Project> handle(GetProjectByIdQuery query);
    List<Project> handle(GetAllProjectsQuery query);
    List<Member> handle(GetAllProjectMembersByProjectId query);
    List<Project> handle(GetProjectsByUsernameQuery query);
    List<Project> handle(GetActiveProjectsByUsernameQuery query);
    List<Project> handle(GetCompleteProjectsByUsernameQuery query);
}
