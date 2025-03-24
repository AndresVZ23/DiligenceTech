package com.deltatech.diligencetech.platform.profiles.domain.model.aggregates;

import com.deltatech.diligencetech.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.deltatech.diligencetech.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {
    @Column
    private Long agentId;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String enterprise;

    protected Profile() {}

    public Profile(CreateProfileCommand command) {
        this.agentId = command.userId();
        this.username = command.username();
        this.name = command.firstName() + " " + command.lastName();
        this.email = command.username();
        this.enterprise = "";
    }
}
