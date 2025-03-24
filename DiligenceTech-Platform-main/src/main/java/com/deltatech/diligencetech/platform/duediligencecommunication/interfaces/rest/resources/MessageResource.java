package com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest.resources;

import java.util.Date;

public record MessageResource(Long id, Long projectId, Long userId, Long destinationUserId, String subject, String message, Date createdAt) {}