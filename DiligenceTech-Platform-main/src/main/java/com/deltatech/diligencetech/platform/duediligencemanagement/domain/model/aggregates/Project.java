package com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.aggregates;

import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.entities.Member;
import com.deltatech.diligencetech.platform.duediligencemanagement.domain.model.valueobjects.*;
import com.deltatech.diligencetech.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
public class Project extends AuditableAbstractAggregateRoot<Project> {
    @Column
    private String name;

    @Column
    private DueDiligenceStatus sellStatus;

    @Column
    private DueDiligenceStatus buyStatus;

    @Column
    @Setter
    private Boolean active;

    @Embedded
    private MembersList membersList;

    public void addMember(Member member) {
        this.membersList.addMember(member);
    }

    public List<Member> getMembers() {
        return this.membersList.members;
    }

    public Project() {
    }

    public Project(String name) {
        this.name = name;
        this.sellStatus = DueDiligenceStatus.PENDING;
        this.buyStatus = DueDiligenceStatus.PENDING;
        this.active = false;
        this.membersList = new MembersList();
    }
}