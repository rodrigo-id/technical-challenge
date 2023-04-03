package cl.meli.technicalchallenge.infraestructure.adapter.mapper;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.domain.model.LogVisitDomainModel;
import cl.meli.technicalchallenge.infraestructure.data.dto.LogDto;
import cl.meli.technicalchallenge.infraestructure.data.entities.LogEntity;
import java.time.LocalDateTime;
import java.util.Comparator;
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

  public List<LogVisitDomainModel> toLogVisitDomainModel(List<LogEntity> logEntityList, List<LogDto> iLogsDto) {
      return iLogsDto.stream()
        .map(logs -> LogVisitDomainModel.builder()
            .setShortUrl(logs.getShortUrlVisited())
            .setTimesVisited(logs.getTimesVisited())
            .setIsActive(
                logEntityList.stream()
                    .filter(logEntity -> logs.getShortUrlVisited().equals(logEntity.getShortUrlVisited()))
                    .findFirst()
                    .map(LogEntity::getActive)
                    .get()
            )
            .setLastTimeVisited(
                lastTimeVisited(logEntityList.stream()
                    .filter(logEntity -> logs.getShortUrlVisited().equals(logEntity.getShortUrlVisited()))
                    .collect(Collectors.toList()))
            )
            .build())
          .collect(Collectors.toList());

  }

  private LocalDateTime lastTimeVisited(List<LogEntity> logEntityList) {
    if (logEntityList.get(0).getActive()) {
      return logEntityList.get(0).getVisitedDate();
    }

    return logEntityList.get(1).getVisitedDate();
  }
}
