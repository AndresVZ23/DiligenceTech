package com.deltatech.diligencetech.platform.duediligencecommunication.domain.services;

import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.aggregates.Message;
import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.queries.*;

import java.util.List;
import java.util.Optional;


public interface MessageQueryService {

    List<Message> handle(GetAllMessagesQuery query);
    Optional<Message> handle(GetMessageByIdQuery query);
    List<Message> handle(GetMessagesByProjectIdQuery query);
}