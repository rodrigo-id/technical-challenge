package cl.meli.technicalchallenge.application;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.domain.port.input.UrlShortUseCase;
import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.shared.ValidatorUtils;
import cl.meli.technicalchallenge.shared.error.DomainException;
import cl.meli.technicalchallenge.shared.error.ErrorCodes;
import java.text.MessageFormat;
import java.util.Date;

public class UrlShortUseCaseImpl implements UrlShortUseCase {

  private final UrlDomainRepository urlDomainRepository;
  private final ValidatorUtils validatorUtils;

  public UrlShortUseCaseImpl(UrlDomainRepository urlDomainRepository, ValidatorUtils validatorUtils) {
    this.urlDomainRepository = urlDomainRepository;
    this.validatorUtils = validatorUtils;
  }

  @Override
  public String createShortUrl(String requestUrl, String serverUrl) {
    if(!validatorUtils.validateUrl(requestUrl)) {
      throw new DomainException(
          ErrorCodes.DOMAIN_INVALID_URL.getCode(),
          MessageFormat.format(ErrorCodes.DOMAIN_INVALID_URL.getMessage(), requestUrl),
          "UrlShortUseCaseImpl.shortUrl",
          ErrorCodes.DOMAIN_INVALID_URL.getStatus());
    }

    UrlDomainModel urlDomainModel =  urlDomainRepository.findUrlByLongUrl(requestUrl);
    if (urlDomainModel.isPresent()) {
      return urlDomainModel.getShortUrl();
    }

    //Aqui se debe aplicar la logica q encoja la url
    String urlHashed = String.valueOf(requestUrl.hashCode());
    String shortUrl = MessageFormat.format("{0}/{1}", serverUrl, urlHashed);

    this.saveUrls(requestUrl, shortUrl);

    return shortUrl;
  }

  private void saveUrls(String longUrl, String shortUrl) {
    UrlDomainModel urlDomainModel = UrlDomainModel.builder()
        .setLongUrl(longUrl)
        .setShortUrl(shortUrl)
        .setCreatedDate(new Date())
        .build();

    urlDomainRepository.saveUrl(urlDomainModel);
  }
}
