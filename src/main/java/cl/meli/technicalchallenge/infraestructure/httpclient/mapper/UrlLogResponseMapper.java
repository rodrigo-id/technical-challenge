package cl.meli.technicalchallenge.infraestructure.httpclient.mapper;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.Status;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlLogResponse;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.Visited;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UrlLogResponseMapper {

  public UrlLogResponse toUrlLogResponse(List<LogDomainModel> logDomainModelList) {
    UrlLogResponse urlLogResponse = new UrlLogResponse();
    urlLogResponse.setVisitedList(
        logDomainModelList.stream()
            .filter(logDomainModel -> logDomainModel.getVisitedDate() != null)
            .map(logVisited ->
                Visited.builder()
                .setVisitedDate(logVisited.getVisitedDate().toString())
                .build())
            .collect(Collectors.toList()));
    urlLogResponse.setStatus(
        logDomainModelList.stream()
            .filter(logDomainModel -> !logDomainModel.isActive())
            .findFirst()
            .map(logDomainModel -> Status.builder()
                .setActive(logDomainModel.isActive())
                .setDeactivateDate(logDomainModel.getDeactivateDate().toString())
                .build())
            .orElse(Status.builder()
                .setActive(true)
                .build()));

    return urlLogResponse;
  }
}
