package cl.meli.technicalchallenge.application;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.domain.port.input.UrlLogUseCase;
import cl.meli.technicalchallenge.domain.port.output.LogDomainRepository;
import java.util.Date;

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
}
