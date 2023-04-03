package cl.meli.technicalchallenge.application;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.domain.port.input.UrlLongUseCase;
import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.shared.error.DomainException;
import cl.meli.technicalchallenge.shared.error.ErrorCodes;
import java.text.MessageFormat;

public class UrlLongUseCaseImpl implements UrlLongUseCase {

  private final UrlDomainRepository urlDomainRepository;

  public UrlLongUseCaseImpl(UrlDomainRepository urlDomainRepository) {
    this.urlDomainRepository = urlDomainRepository;
  }

  @Override
  public String retrieveLongUrl(String shortUrl) {
    UrlDomainModel urlDomainModel = urlDomainRepository.findUrlByShortUrl(shortUrl);

    if(!urlDomainModel.isPresent()) {
      throw new DomainException(
          ErrorCodes.DOMAIN_SHORT_URL_NOT_FOUND.getCode(),
          MessageFormat.format(ErrorCodes.DOMAIN_SHORT_URL_NOT_FOUND.getMessage(), shortUrl),
          "UrlLongUseCaseImpl.retrieveLongUrl",
          ErrorCodes.DOMAIN_SHORT_URL_NOT_FOUND.getStatus());
    }
    return urlDomainModel.getLongUrl();
  }
}
