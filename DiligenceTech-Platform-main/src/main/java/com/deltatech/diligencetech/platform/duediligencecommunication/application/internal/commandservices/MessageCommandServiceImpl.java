package com.deltatech.diligencetech.platform.duediligencecommunication.application.internal.commandservices;

import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.aggregates.Message;
import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.commands.CreateMessageCommand;
import com.deltatech.diligencetech.platform.duediligencecommunication.domain.services.MessageCommandService;
import com.deltatech.diligencetech.platform.duediligencecommunication.infrastructure.persistence.jpa.repositories.MessageRepository;
import com.deltatech.diligencetech.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MessageCommandServiceImpl implements MessageCommandService {
    private final MessageRepository emailRepository;
    private final UserRepository userRepository;

    public MessageCommandServiceImpl(MessageRepository emailRepository, UserRepository userRepository) {
        this.emailRepository = emailRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Message> handle(CreateMessageCommand command) {
        var destinationUser = userRepository.findByUsername(command.destinationUsername());
        if (destinationUser.isEmpty()) {
            return Optional.empty();
        }
        var destinationUserId = destinationUser.get().getId();
        var email = new Message(command, destinationUserId);
        emailRepository.save(email);
        return Optional.of(email);
    }

}
