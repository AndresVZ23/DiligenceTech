package com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.commands;

public record CreateMessageCommand(Long projectId, Long userId, String destinationUsername, String subject, String message) {}