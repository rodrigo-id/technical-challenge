package cl.meli.technicalchallenge.application;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.domain.model.LogVisitDomainModel;
import cl.meli.technicalchallenge.domain.port.input.UrlLogUseCase;
import cl.meli.technicalchallenge.domain.port.output.LogDomainRepository;
import cl.meli.technicalchallenge.shared.error.DomainException;
import cl.meli.technicalchallenge.shared.error.ErrorCodes;
import java.time.LocalDateTime;
import java.util.List;

public class UrlLogUseCaseImpl implements UrlLogUseCase {
  private final LogDomainRepository logDomainRepository;

  public UrlLogUseCaseImpl(LogDomainRepository logDomainRepository) {
    this.logDomainRepository = logDomainRepository;
  }

  @Override
  public void save(String url) {
    logDomainRepository.saveLongInfo(LogDomainModel.builder()
            .setShortUrlVisited(url)
            .setVisitedDate(LocalDateTime.now())
            .setIsActive(true)
            .build());
  }

  @Override
  public void deactivate(String url) {
    logDomainRepository.saveLongInfo(LogDomainModel.builder()
        .setShortUrlVisited(url)
        .setDeactivateDate(LocalDateTime.now())
        .setIsActive(false)
        .build());
  }

  @Override
  public List<LogDomainModel> retrieveShortUrlInfo(String url) {
    List<LogDomainModel> logDomainModelList = logDomainRepository.retrieveShortUrlInfo(url);
    if (logDomainModelList.isEmpty()) {
      throw new DomainException(
          ErrorCodes.DOMAIN_SHOW_LOG_INFO.getCode(),
          ErrorCodes.DOMAIN_SHOW_LOG_INFO.getMessage(),
          "UrlLogUseCaseImpl.retrieveUrlInfo",
          ErrorCodes.DOMAIN_SHOW_LOG_INFO.getStatus());
    }
    return logDomainRepository.retrieveShortUrlInfo(url);
  }

  @Override
  public List<LogVisitDomainModel> retrieveTimesVisitForShortUrl() {
    return logDomainRepository.retrieveTimesVisitForShortUrl();
  }


}
