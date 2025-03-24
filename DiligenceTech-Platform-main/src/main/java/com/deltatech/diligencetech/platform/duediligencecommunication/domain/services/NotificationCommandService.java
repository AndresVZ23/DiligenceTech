package com.deltatech.diligencetech.platform.duediligencecommunication.domain.services;

import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.aggregates.Notification;
import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.commands.CreateNotificationCommand;
import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.commands.DeleteNotificationCommand;

import java.util.Optional;

public interface NotificationCommandService{
    Optional<Notification> handle(CreateNotificationCommand command);
    void handle(DeleteNotificationCommand command);
}