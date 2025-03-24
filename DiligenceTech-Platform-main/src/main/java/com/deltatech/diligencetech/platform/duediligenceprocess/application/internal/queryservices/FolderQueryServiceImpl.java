package com.deltatech.diligencetech.platform.duediligenceprocess.application.internal.queryservices;

import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.aggregates.Folder;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.queries.*;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.services.FolderQueryService;
import com.deltatech.diligencetech.platform.duediligenceprocess.infrastructure.persistence.jpa.repositories.AreaRepository;
import com.deltatech.diligencetech.platform.duediligenceprocess.infrastructure.persistence.jpa.repositories.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FolderQueryServiceImpl implements FolderQueryService {
  private final FolderRepository folderRepository;
  private final AreaRepository areaRepository;

  public FolderQueryServiceImpl(FolderRepository folderRepository, AreaRepository areaRepository) {
    this.folderRepository = folderRepository;
    this.areaRepository = areaRepository;
  }


  @Override
  public List<Folder> handle(GetFolderByAreaIdQuery query) {
    var area = areaRepository.findById(query.areaId());
    if(area.isEmpty()) throw new IllegalArgumentException("Area does not exist");
    return folderRepository.findByParent(area.get());

  }

  @Override
  public Optional<Folder> handle(GetFolderByIdQuery query) {
    return folderRepository.findById(query.folderId());
  }

  @Override
  public List<Folder> handle(GetAllFoldersQuery query) {
    return folderRepository.findAll();
  }
}
