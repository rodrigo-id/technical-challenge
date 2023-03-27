package cl.meli.technicalchallenge.domain.port.output;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;

public interface UrlDomainRepository {

  void saveUrl(UrlDomainModel urlDomainModel);

  UrlDomainModel findUrlByLongUrl(String url);
  UrlDomainModel findUrlByShortUrl(String url);
  Long deleteShortUrl(String url);
}
