package com.deltatech.diligencetech.platform.duediligencemanagement.application.internal.commandservices;

import com.deltatech.diligencetech.platform.duediligencemanagement.application.internal.outboundservices.acl.DueDiligenceManagementExternalsService;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.aggregates.Project;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.commands.ActivateProjectCommand;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.commands.CreateInactiveProjectCommand;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.commands.DeactivateProjectCommand;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.entities.Member;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.valueobjects.DueDiligenceRole;
import com.deltatech.diligencetech.platform.duediligencemanagement.infrastructure.persistence.jpa.repositories.MemberRepository;
import com.deltatech.diligencetech.platform.duediligencemanagement.infrastructure.persistence.jpa.repositories.ProjectRepository;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.services.ProjectCommandService;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class ProjectCommandServiceImpl implements ProjectCommandService {

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final DueDiligenceManagementExternalsService dueDiligenceManagementExternalsService;

    public ProjectCommandServiceImpl(ProjectRepository projectRepository, MemberRepository memberRepository, DueDiligenceManagementExternalsService dueDiligenceManagementExternalsService) {
        this.projectRepository = projectRepository;
        this.memberRepository = memberRepository;
        this.dueDiligenceManagementExternalsService = dueDiligenceManagementExternalsService;
    }

    @Override
    public Optional<Long> handle(CreateInactiveProjectCommand command) {
        var project = new Project(command.name());
        // get agentsId
        var members = new ArrayList<Member>(emptyList());
        // only fail spot
        var agentsId = dueDiligenceManagementExternalsService.fetchAgentsIdByUsernames(command.agentsUsernames());
        /*
        // check all agentsId are valid
        if (agentsId.size() != command.agentsUsernames().size()) {
            throw new IllegalArgumentException("Some agents are invalid");
        }
         */

        // try saving each agentsId
        agentsId.forEach(agentId -> {
            members.add(new Member(agentId, project));
            members.getLast().setAgentRole(command.agentsRoles().get(agentsId.indexOf(agentId)) ? DueDiligenceRole.BUY_SIDE : DueDiligenceRole.SELL_SIDE);
            project.addMember(members.getLast());
        });
        try {
            projectRepository.save(project);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving project");
        }
        /*
        try {
            memberRepository.saveAll(members);
        } catch (Exception e) {
            projectRepository.delete(project);
            throw new IllegalArgumentException("Error saving members");
        }
         */
        return Optional.of(project.getId());
    }

    @Override
    public void handle(DeactivateProjectCommand command) {
        var project = projectRepository.findById(command.projectId()).orElseThrow(() -> new IllegalArgumentException("Project not found"));
        project.setActive(true);
        projectRepository.save(project);
    }

    @Override
    public void handle(ActivateProjectCommand command) {

    }
}