package com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest;

import com.deltatech.diligencetech.platform.duediligencecommunication.domain.model.queries.*;
import com.deltatech.diligencetech.platform.duediligencecommunication.domain.services.MessageCommandService;
import com.deltatech.diligencetech.platform.duediligencecommunication.domain.services.MessageQueryService;
import com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest.resources.CreateMessageResource;
import com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest.resources.MessageResource;
import com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest.transform.CreateMessageCommandFromResourceAssembler;
import com.deltatech.diligencetech.platform.duediligencecommunication.interfaces.rest.transform.MessageResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/messages", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Messages")
public class MessageController {

    private final MessageQueryService messageQueryService;
    private final MessageCommandService messageCommandService;

    public MessageController(MessageQueryService emailQueryService, MessageCommandService emailCommandService) {
        this.messageQueryService = emailQueryService;
        this.messageCommandService = emailCommandService;
    }

    @GetMapping
    public ResponseEntity<List<MessageResource>> getAllEmails() {
        var getAllEmailsQuery = new GetAllMessagesQuery();
        var emails = messageQueryService.handle(getAllEmailsQuery);
        var emailsResources = emails.stream()
                .map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(emailsResources);
    }
    @GetMapping("/{emailId}")
    public ResponseEntity<MessageResource> getEmailById(@PathVariable Long emailId) {
        var getEmailByIdQuery = new GetMessageByIdQuery(emailId);
        var email = messageQueryService.handle(getEmailByIdQuery);
        if (email.isEmpty()) return ResponseEntity.notFound().build();
        var emailResource = MessageResourceFromEntityAssembler.toResourceFromEntity(email.get());
        return ResponseEntity.ok(emailResource);
    }
    @PostMapping
    public ResponseEntity<MessageResource> createEmail(@RequestBody CreateMessageResource resource) {
        var createEmailCommand = CreateMessageCommandFromResourceAssembler.toCommandFromResource(resource);
        var email = messageCommandService.handle(createEmailCommand);
        if (email.isEmpty()) return ResponseEntity.badRequest().build();
        var emailResource = MessageResourceFromEntityAssembler.toResourceFromEntity(email.get());
        return new ResponseEntity<>(emailResource, HttpStatus.CREATED);
    }
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<MessageResource>> getMessagesByProjectId(@PathVariable Long projectId) {
        var getMessagesByProjectIdQuery = new GetMessagesByProjectIdQuery(projectId);
        var messages = messageQueryService.handle(getMessagesByProjectIdQuery);
        var messagesResources = messages.stream()
                .map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(messagesResources);
    }

}
