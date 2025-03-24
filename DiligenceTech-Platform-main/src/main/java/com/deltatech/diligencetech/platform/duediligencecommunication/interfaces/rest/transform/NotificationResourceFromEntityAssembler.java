package com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest.transform;

import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.aggregates.Notification;
import com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification entity) {
        return new NotificationResource(entity.getId(), entity.getAgentId(), entity.getType(), entity.getContent());
    }
}