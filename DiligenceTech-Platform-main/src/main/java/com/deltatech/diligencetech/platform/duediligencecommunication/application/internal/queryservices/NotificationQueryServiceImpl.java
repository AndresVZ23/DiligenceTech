package com.deltatech.diligencetech.platform.duediligencecommunication.application.internal.queryservices;

import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.aggregates.Notification;
import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.queries.GetAllNotificationsQuery;
import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.queries.GetNotificationByIdQuery;
import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.queries.GetNotificationsByAgentIdQuery;
import com.deltatech.diligencetech.platform.duediligencecommunication.domain.services.NotificationQueryService;
import com.deltatech.diligencetech.platform.duediligencecommunication.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> handle(GetAllNotificationsQuery query) {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> handle(GetNotificationByIdQuery query) {
        return notificationRepository.findById(query.notificationId());
    }

    @Override
    public List<Notification> handle(GetNotificationsByAgentIdQuery query){
        return notificationRepository.findByAgentId(query.agentId());
    }
}
