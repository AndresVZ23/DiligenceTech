package com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.commands;

public record CreateNotificationCommand(Long agentId, String type, String content){}