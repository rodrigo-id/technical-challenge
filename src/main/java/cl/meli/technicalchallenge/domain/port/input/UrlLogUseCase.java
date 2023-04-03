package cl.meli.technicalchallenge.domain.port.input;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import java.util.List;

public interface UrlLogUseCase {

  void save(String url);
  void deactivate(String url);
  List<LogDomainModel> retrieveShortUrlInfo(String url);

}
