package com.deltatech.diligencetech.platform.duediligenceprocess.domain.services;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.aggregates.Folder;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.commands.CreateFolderCommand;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.commands.UpdateFolderNameCommand;

import java.util.Optional;

public interface FolderCommandService {

  Long handle(CreateFolderCommand command);
  Optional<Folder> handle(UpdateFolderNameCommand command);

}
