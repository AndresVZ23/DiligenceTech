package com.deltatech.diligencetech.platform.duediligenceprocess.domain.services;

import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.entities.Document;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface DocumentQueryService {

  List<Document> handle(GetDocumentsByFolderIdQuery query);
  Optional<Document> handle(GetDocumentByIdQuery query);
  List<Document> handle(GetAllDocumentsQuery query);

}
