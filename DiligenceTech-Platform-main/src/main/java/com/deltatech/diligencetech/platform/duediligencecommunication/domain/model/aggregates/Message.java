package com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.aggregates;

import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.commands.CreateMessageCommand;
import com.deltatech.diligencetech.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;



@Getter
@Entity
public class Message extends AuditableAbstractAggregateRoot<Message> {
    @Column
    private Long projectId;

    @Column
    private Long userId;

    @Column
    private Long destinationUserId;

    @Column
    private String subject;

    @Column
    private String message;

    public Message() {
    }

    public Message(CreateMessageCommand command, Long destinationUserId) {
        this.projectId = command.projectId();
        this.userId = command.userId();
        this.destinationUserId = destinationUserId;
        this.subject = command.subject();
        this.message = command.message();
    }

}
