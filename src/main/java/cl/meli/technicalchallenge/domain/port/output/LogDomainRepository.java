package cl.meli.technicalchallenge.domain.port.output;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.domain.model.LogVisitDomainModel;
import java.util.List;

public interface LogDomainRepository {
  void saveLongInfo(LogDomainModel logDomainModel);
  List<LogDomainModel> retrieveShortUrlInfo(String url);
  List<LogVisitDomainModel> retrieveTimesVisitForShortUrl();
}
