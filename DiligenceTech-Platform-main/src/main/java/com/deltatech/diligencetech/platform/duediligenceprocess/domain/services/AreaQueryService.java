package com.deltatech.diligencetech.platform.duediligenceprocess.domain.services;

import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.aggregates.Area;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.queries.GetAllAreasQuery;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.queries.GetAreaByIdQuery;
import com.deltatech.diligencetech.platform.duediligenceprocess.domain.model.queries.GetAreasByProjectIdQuery;

import java.util.List;
import java.util.Optional;

public interface AreaQueryService {

  List<Area> handle(GetAreasByProjectIdQuery query);
  Optional<Area> handle(GetAreaByIdQuery query);
  List<Area> handle(GetAllAreasQuery query);

}
