package com.deltatech.diligencetech.platform.duediligenceprocess.interfaces.rest.transform;

import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.commands.UpdateDocumentCommand;

public class UpdateDocumentNameCommandFromResourceAssembler {
  public static UpdateDocumentCommand toCommandFromResource(Long documentId, String filename) {
    return new UpdateDocumentCommand(documentId, filename);
  }
}
