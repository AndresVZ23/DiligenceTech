package com.deltatech.diligencetech.platform.duediligenceprocess.domain.services;

import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.commands.*;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.entities.Document;

import java.util.Optional;

public interface DocumentCommandService {

  Long handle(CreateDocumentCommand command);
  Optional<Document> handle(UpdateDocumentCommand command);
  void handle(DeleteDocumentCommand command);

}
