package com.deltatech.diligencetech.platform.duediligencemanagement.interfaces.rest;

import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.commands.DeactivateProjectCommand;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.queries.*;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.services.ProjectCommandService;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.services.ProjectQueryService;
import com.deltatech.diligencetech.platform.duediligencemanagement.interfaces.rest.resources.*;
import com.deltatech.diligencetech.platform.duediligencemanagement.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/due-diligence-projects", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Due Diligence Projects", description = "Due Diligence Management Endpoints")
public class ProjectController {

    private final ProjectCommandService projectCommandService;
    private final ProjectQueryService projectQueryService;

    public ProjectController(ProjectCommandService projectCommandService, ProjectQueryService projectQueryService) {
        this.projectCommandService = projectCommandService;
        this.projectQueryService = projectQueryService;
    }

    @PostMapping
    public ResponseEntity<ProjectResource> createDueDiligenceProject(@RequestBody CreateProjectResource createProjectResource) {
        var createDueDiligenceProjectCommand = CreateProjectCommandFromResourceAssembler.toCommandFromResource(createProjectResource);
        // create project
        var projectId = projectCommandService.handle(createDueDiligenceProjectCommand);
        if(projectId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        // get project by id
        var getDueDiligenceProjectByIdQuery = new GetProjectByIdQuery(projectId.get());
        var dueDiligenceProject = projectQueryService.handle(getDueDiligenceProjectByIdQuery);
        if (dueDiligenceProject.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var dueDiligenceProjectResource = ProjectResourceFromEntityAssembler.toResourceFromEntity(dueDiligenceProject.get());
        return new ResponseEntity<>(dueDiligenceProjectResource, HttpStatus.CREATED);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<ProjectResource>> getDueDiligenceProjectsByUsername(@PathVariable String username) {
        var getDueDiligenceProjectsByUserIdQuery = new GetProjectsByUsernameQuery(username);
        var dueDiligenceProjects = projectQueryService.handle(getDueDiligenceProjectsByUserIdQuery);
        var dueDiligenceProjectResources = dueDiligenceProjects.stream().map(ProjectResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(dueDiligenceProjectResources);
    }

    @GetMapping("/user/complete/{username}")
    public ResponseEntity<List<ProjectResource>> getCompletedDueDiligenceProjectsByUsername(@PathVariable String username) {
        var getDueDiligenceProjectsByUserIdQuery = new GetActiveProjectsByUsernameQuery(username);
        var dueDiligenceProjects = projectQueryService.handle(getDueDiligenceProjectsByUserIdQuery);
        var dueDiligenceProjectResources = dueDiligenceProjects.stream().map(ProjectResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(dueDiligenceProjectResources);
    }

    @GetMapping("/user/active/{username}")
    public ResponseEntity<List<ProjectResource>> getActiveDueDiligenceProjectsByUsername(@PathVariable String username) {
        var getDueDiligenceProjectsByUserIdQuery = new GetCompleteProjectsByUsernameQuery(username);
        var dueDiligenceProjects = projectQueryService.handle(getDueDiligenceProjectsByUserIdQuery);
        var dueDiligenceProjectResources = dueDiligenceProjects.stream().map(ProjectResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(dueDiligenceProjectResources);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResource> getDueDiligenceProjectById(@PathVariable Long projectId) {
        var getDueDiligenceProjectByIdQuery = new GetProjectByIdQuery(projectId);
        var dueDiligenceProject = projectQueryService.handle(getDueDiligenceProjectByIdQuery);
        if (dueDiligenceProject.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var dueDiligenceProjectResource = ProjectResourceFromEntityAssembler.toResourceFromEntity(dueDiligenceProject.get());
        return ResponseEntity.ok(dueDiligenceProjectResource);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResource>> getAllDueDiligenceProjects() {
        var getAllDueDiligenceProjectsQuery = new GetAllProjectsQuery();
        var dueDiligenceProjects = projectQueryService.handle(getAllDueDiligenceProjectsQuery);
        var dueDiligenceProjectResources = dueDiligenceProjects.stream().map(ProjectResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(dueDiligenceProjectResources);
    }

    @PutMapping("/complete-deactivate/{projectId}")
    public ResponseEntity<Void> deactivateDueDiligenceProject(@PathVariable Long projectId) {
        var deactivateDueDiligenceProjectCommand = new DeactivateProjectCommand(projectId);
        projectCommandService.handle(deactivateDueDiligenceProjectCommand);
        return ResponseEntity.ok().build();
    }

}