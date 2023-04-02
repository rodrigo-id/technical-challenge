package cl.meli.technicalchallenge.domain.port.output;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import java.util.List;

public interface LogDomainRepository {
  void save(LogDomainModel logDomainModel);
  List<LogDomainModel> retrieveUrlInfo(String url);
}
