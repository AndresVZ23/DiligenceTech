package com.deltatech.diligencetech.platform.duediligencecommunication.application.internal.queryservices;

import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.aggregates.Message;
import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.queries.*;
import com.deltatech.diligencetech.platform.duediligencecommunication.domain.services.MessageQueryService;
import com.deltatech.diligencetech.platform.duediligencecommunication.infrastructure.persistence.jpa.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MessageQueryServiceImpl implements MessageQueryService {

    private final MessageRepository emailRepository;

    public MessageQueryServiceImpl(MessageRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public List<Message> handle(GetAllMessagesQuery query) {
        return emailRepository.findAll();
    }
    @Override
    public Optional<Message> handle (GetMessageByIdQuery query) {
        return emailRepository.findById(query.emailId());
    }

    @Override
    public List<Message> handle(GetMessagesByProjectIdQuery query) {
        return emailRepository.findAllByProjectId(query.projectId());
    }
}
