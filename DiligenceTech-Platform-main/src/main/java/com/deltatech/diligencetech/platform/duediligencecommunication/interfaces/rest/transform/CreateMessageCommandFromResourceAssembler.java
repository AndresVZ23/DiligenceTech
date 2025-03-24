package com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest.transform;

import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.commands.CreateMessageCommand;
import com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest.resources.CreateMessageResource;

public class CreateMessageCommandFromResourceAssembler {
    public static CreateMessageCommand toCommandFromResource(CreateMessageResource resource){
        return new CreateMessageCommand(resource.projectId(), resource.userId(), resource.destinationUsername(), resource.subject(), resource.message());
    }
}