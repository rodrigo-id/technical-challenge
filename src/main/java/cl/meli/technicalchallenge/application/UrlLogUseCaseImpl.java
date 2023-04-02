package cl.meli.technicalchallenge.application;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.domain.port.input.UrlLogUseCase;
import cl.meli.technicalchallenge.domain.port.output.LogDomainRepository;
import cl.meli.technicalchallenge.shared.error.DomainException;
import cl.meli.technicalchallenge.shared.error.ErrorCodes;
import java.util.Date;
import java.util.List;

public class UrlLogUseCaseImpl implements UrlLogUseCase {
  private final LogDomainRepository logDomainRepository;

  public UrlLogUseCaseImpl(LogDomainRepository logDomainRepository) {
    this.logDomainRepository = logDomainRepository;
  }

  @Override
  public void save(String url) {
    logDomainRepository.save(LogDomainModel.builder()
            .setShortUrlVisited(url)
            .setVisitedDate(new Date())
            .setIsActive(true)
            .build());
  }

  @Override
  public void deactivate(String url) {
    logDomainRepository.save(LogDomainModel.builder()
        .setShortUrlVisited(url)
        .setDeactivateDate(new Date())
        .setIsActive(false)
        .build());
  }

  @Override
  public List<LogDomainModel> retrieveUrlInfo(String url) {
    List<LogDomainModel> logDomainModelList = logDomainRepository.retrieveUrlInfo(url);
    if (logDomainModelList.isEmpty()) {
      throw new DomainException(
          ErrorCodes.DOMAIN_SHOW_LOG_INFO.getCode(),
          ErrorCodes.DOMAIN_SHOW_LOG_INFO.getMessage(),
          "UrlLogUseCaseImpl.retrieveUrlInfo",
          ErrorCodes.DOMAIN_SHOW_LOG_INFO.getStatus());
    }
    return logDomainRepository.retrieveUrlInfo(url);
  }


}
