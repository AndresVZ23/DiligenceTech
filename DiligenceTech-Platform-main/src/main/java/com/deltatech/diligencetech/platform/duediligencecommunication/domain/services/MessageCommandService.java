package com.deltatech.diligencetech.platform.duediligencecommunication.domain.services;

import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.aggregates.Message;
import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.commands.CreateMessageCommand;

import java.util.Optional;

public interface MessageCommandService {
    Optional<Message> handle(CreateMessageCommand command);
}