package com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest.transform;

import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.commands.CreateNotificationCommand;
import com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource){
        return new CreateNotificationCommand( resource.agentId(), resource.type(), resource.content());
    }
}