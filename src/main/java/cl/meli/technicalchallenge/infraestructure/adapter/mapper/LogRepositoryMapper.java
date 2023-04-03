package cl.meli.technicalchallenge.infraestructure.adapter.mapper;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.infraestructure.data.entities.LogEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class LogRepositoryMapper {

  public LogEntity toLogEntity(LogDomainModel logDomainModel) {
    LogEntity logEntity = new LogEntity();
    logEntity.setVisitedDate(logDomainModel.getVisitedDate());
    logEntity.setShortUrlVisited(logDomainModel.getShortUrlVisited());
    logEntity.setDeactivateDate(logDomainModel.getDeactivateDate());
    logEntity.setActive(logDomainModel.isActive());
    logEntity.setCampaignType("MELI");
    return logEntity;
  }

  public List<LogDomainModel> toLogDomainModelList(List<LogEntity> logEntityList) {
    return logEntityList.stream().map(logEntity ->
        LogDomainModel.builder()
            .setIsActive(logEntity.getActive())
            .setDeactivateDate(logEntity.getDeactivateDate())
            .setVisitedDate(logEntity.getVisitedDate())
            .build())
        .collect(Collectors.toList());
  }
}
