package com.deltatech.diligencetech.platform.duediligenceprocess.domain.services;

import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.aggregates.Folder;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface FolderQueryService {

  List<Folder> handle(GetFolderByAreaIdQuery query);
  Optional<Folder> handle(GetFolderByIdQuery query);
  List<Folder> handle(GetAllFoldersQuery query);

}
