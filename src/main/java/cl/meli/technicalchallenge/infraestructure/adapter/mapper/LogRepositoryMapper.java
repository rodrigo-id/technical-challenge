package cl.meli.technicalchallenge.infraestructure.adapter.mapper;

import cl.meli.technicalchallenge.infraestructure.data.urlstorage.entities.LogEntity;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class LogRepositoryMapper {

  public LogEntity toLogEntity(String url) {
    LogEntity logEntity = new LogEntity();
    logEntity.setVisitedDate(new Date());
    logEntity.setShortUrlVisited(url);
    return logEntity;
  }
}
