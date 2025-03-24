package com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.entities;

import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.aggregates.Project;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.valueobjects.DueDiligenceRole;
import com.deltatech.diligencetech.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Entity
public class Member extends AuditableModel {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Getter
    @JoinColumn(name = "project_id")
    private Project project;

    @Getter
    @Column
    private Long userId;

    @Getter
    @Setter
    @Column
    private DueDiligenceRole agentRole;

    @Getter
    @Column
    private Boolean hasAcceptedInvitation;

    public Member() {
        this.project = new Project();
        this.userId = 0L;
        this.agentRole = DueDiligenceRole.BUY_SIDE;
        this.hasAcceptedInvitation = false;
    }

    public Member(Long userId, Project project) {
        this.userId = userId;
        this.hasAcceptedInvitation = true;
        this.project = project;
    }

    public Member(Long userId, Project project, DueDiligenceRole agentRole) {
        this.userId = userId;
        this.hasAcceptedInvitation = true;
        this.project = project;
        this.agentRole = agentRole;
    }

}