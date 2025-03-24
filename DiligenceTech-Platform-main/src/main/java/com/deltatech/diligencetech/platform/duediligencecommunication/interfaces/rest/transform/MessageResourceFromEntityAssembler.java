package com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest.transform;

import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.aggregates.Message;
import com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest.resources.MessageResource;

public class MessageResourceFromEntityAssembler {
    public static MessageResource toResourceFromEntity(Message entity) {
        return new MessageResource(entity.getId(), entity.getProjectId(), entity.getUserId(), entity.getDestinationUserId(), entity.getSubject(), entity.getMessage(), entity.getCreatedAt());
    }
}