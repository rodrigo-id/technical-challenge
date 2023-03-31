package cl.meli.technicalchallenge.infraestructure.config;

import cl.meli.technicalchallenge.application.UrlDeleteUseCaseImpl;
import cl.meli.technicalchallenge.application.UrlLongUseCaseImpl;
import cl.meli.technicalchallenge.application.UrlShortUseCaseImpl;
import cl.meli.technicalchallenge.domain.port.input.UrlDeleteUseCase;
import cl.meli.technicalchallenge.domain.port.input.UrlLongUseCase;
import cl.meli.technicalchallenge.domain.port.input.UrlShortUseCase;
import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.domain.service.HashService;
import cl.meli.technicalchallenge.domain.service.impl.HashServiceImpl;
import cl.meli.technicalchallenge.shared.utils.ValidatorUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanCustomConfig {

  @Bean
  public HashService hashServiceImpl() {
    return new HashServiceImpl();
  }

  @Bean
  public UrlShortUseCase urlUseCaseImpl(UrlDomainRepository urlDomainRepository, HashService hashService) {
    return new UrlShortUseCaseImpl(
        urlDomainRepository,
        ValidatorUtil.getInstance(),
        hashService);
  }

  @Bean
  public UrlLongUseCase urlLongUseCaseImpl(UrlDomainRepository urlDomainRepository) {
    return new UrlLongUseCaseImpl(urlDomainRepository);
  }

  @Bean
  public UrlDeleteUseCase urlDeleteUseCase(UrlDomainRepository urlDomainRepository) {
    return new UrlDeleteUseCaseImpl(urlDomainRepository, ValidatorUtil.getInstance());
  }
}
