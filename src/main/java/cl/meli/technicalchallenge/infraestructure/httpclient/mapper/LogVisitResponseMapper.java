package cl.meli.technicalchallenge.infraestructure.httpclient.mapper;

import cl.meli.technicalchallenge.domain.model.LogVisitDomainModel;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.LogVisitResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class LogVisitResponseMapper {

  public List<LogVisitResponse> toLogVisitResponse(List<LogVisitDomainModel> logVisitDomainModels) {
    return logVisitDomainModels.stream()
        .map(logVisit -> LogVisitResponse.builder()
            .setShortUrl(logVisit.getShortUrl())
            .setTimeVisited(logVisit.getTimesVisited())
            .setLastTimeVisited(logVisit.getLastTimeVisited())
            .setActive(logVisit.getActive())
            .build())
        .collect(Collectors.toList());
  }
}
