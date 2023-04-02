package cl.meli.technicalchallenge.infraestructure.adapter.mapper;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.infraestructure.data.urlstorage.entities.LogEntity;
import org.springframework.stereotype.Component;

@Component
public class LogRepositoryMapper {

  public LogEntity toLogEntity(LogDomainModel logDomainModel) {
    LogEntity logEntity = new LogEntity();
    logEntity.setVisitedDate(logDomainModel.getVisitedDate());
    logEntity.setShortUrlVisited(logDomainModel.getShortUrlVisited());
    logEntity.setDeactivateDate(logDomainModel.getDeactivateDate());
    logEntity.setActive(logDomainModel.isActive());
    return logEntity;
  }
}
