package cl.meli.technicalchallenge.application;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.domain.port.input.UrlShortUseCase;
import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.domain.service.HashService;
import cl.meli.technicalchallenge.shared.ValidatorUtils;;
import cl.meli.technicalchallenge.shared.error.DomainException;
import cl.meli.technicalchallenge.shared.error.ErrorCodes;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Date;

public class UrlShortUseCaseImpl implements UrlShortUseCase {

  private final UrlDomainRepository urlDomainRepository;
  private final ValidatorUtils validatorUtils;
  private final HashService hashService;

  public UrlShortUseCaseImpl(
      UrlDomainRepository urlDomainRepository,
      ValidatorUtils validatorUtils,
      HashService hashService) {
    this.urlDomainRepository = urlDomainRepository;
    this.validatorUtils = validatorUtils;
    this.hashService = hashService;
  }

  @Override
  public String createShortUrl(String requestUrl, String serverUrl) {

    validatorUtils.validateUrl(requestUrl);

    UrlDomainModel urlDomainModel =  urlDomainRepository.findUrlByLongUrl(requestUrl);
    if (urlDomainModel.isPresent()) {
      return urlDomainModel.getShortUrl();
    }

    var urlHashed = new StringBuffer();
    try {
      urlHashed.append(hashService.hash(requestUrl));
    } catch (NoSuchAlgorithmException ex) {
      throw new DomainException(
          ErrorCodes.DOMAIN_GENERATE_HASH.getCode(),
          ErrorCodes.DOMAIN_GENERATE_HASH.getMessage(),
          "UrlShortUseCaseImpl.createShortUrl",
          ex,
          ErrorCodes.DOMAIN_GENERATE_HASH.getStatus()
      );
    }

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
