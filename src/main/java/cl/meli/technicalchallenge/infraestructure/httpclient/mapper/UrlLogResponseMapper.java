package cl.meli.technicalchallenge.infraestructure.httpclient.mapper;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.Status;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlLogResponse;
import cl.meli.technicalchallenge.infraestructure.httpclient.models.Visited;
import java.util.Comparator;
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
                    .setDate(logVisited.getVisitedDate().toString())
                    .build())
            .collect(Collectors.toList()));
    urlLogResponse.setStatus(
        defineStatus(
            logDomainModelList.stream()
                .filter(logDomainModel -> logDomainModel.getDeactivateDate() != null)
                .collect(Collectors.toList()),
            logDomainModelList.stream()
                .filter(logDomainModel -> logDomainModel.getVisitedDate() != null)
                .collect(Collectors.toList())));

    return urlLogResponse;
  }

  private Status defineStatus(List<LogDomainModel> deactiveVisitList, List<LogDomainModel> activeVisitList) {
    activeVisitList.sort(Comparator.comparing(LogDomainModel::getVisitedDate).reversed());
    deactiveVisitList.sort(Comparator.comparing(LogDomainModel::getDeactivateDate).reversed());

    if(!deactiveVisitList.isEmpty() &&
        activeVisitList.get(0).getVisitedDate().isBefore(deactiveVisitList.get(0).getDeactivateDate())) {

      return Status.builder()
          .setActive(false)
          .setDeactivateDate(deactiveVisitList.get(0).getDeactivateDate().toString())
          .build();
    }

    return Status.builder()
        .setActive(true)
        .build();
  }
}
