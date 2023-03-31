package cl.meli.technicalchallenge.application;

import cl.meli.technicalchallenge.domain.port.input.UrlDeleteUseCase;
import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.shared.utils.ValidatorUtil;
import cl.meli.technicalchallenge.shared.error.DomainException;
import cl.meli.technicalchallenge.shared.error.ErrorCodes;
import java.text.MessageFormat;

public class UrlDeleteUseCaseImpl implements UrlDeleteUseCase {
  private final UrlDomainRepository urlDomainRepository;
  private final ValidatorUtil validatorUtil;

  public UrlDeleteUseCaseImpl(UrlDomainRepository urlDomainRepository, ValidatorUtil validatorUtil) {
    this.urlDomainRepository = urlDomainRepository;
    this.validatorUtil = validatorUtil;
  }

  @Override
  public void deleteShortUrl(String url) {
    validatorUtil.validateUrl(url);

    if (urlDomainRepository.deleteShortUrl(url) == 0) {
      throw new DomainException(
          ErrorCodes.DOMAIN_SHORT_URL_DELETE.getCode(),
          MessageFormat.format(ErrorCodes.DOMAIN_SHORT_URL_DELETE.getMessage(), url),
          "UrlDeleteUseCaseImpl.deleteShortUrl",
          ErrorCodes.DOMAIN_SHORT_URL_DELETE.getStatus());
    }
  }
}
