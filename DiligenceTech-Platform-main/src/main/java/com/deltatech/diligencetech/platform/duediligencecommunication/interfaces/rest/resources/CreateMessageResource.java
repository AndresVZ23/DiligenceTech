package com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest.resources;

public record CreateMessageResource(Long projectId, Long userId, String destinationUsername, String subject, String message) {}