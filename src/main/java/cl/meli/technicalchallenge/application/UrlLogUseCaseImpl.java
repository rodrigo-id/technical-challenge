package cl.meli.technicalchallenge.application;

import cl.meli.technicalchallenge.domain.port.input.UrlLogUseCase;
import cl.meli.technicalchallenge.domain.port.output.LogDomainRepository;

public class UrlLogUseCaseImpl implements UrlLogUseCase {
  private final LogDomainRepository logDomainRepository;

  public UrlLogUseCaseImpl(LogDomainRepository logDomainRepository) {
    this.logDomainRepository = logDomainRepository;
  }

  @Override
  public void save(String url) {
    logDomainRepository.save(url);
  }
}
