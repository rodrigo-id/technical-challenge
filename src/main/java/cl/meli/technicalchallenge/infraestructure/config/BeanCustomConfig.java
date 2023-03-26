package cl.meli.technicalchallenge.infraestructure.config;

import cl.meli.technicalchallenge.application.UrlDeleteUseCaseImpl;
import cl.meli.technicalchallenge.application.UrlLongUseCaseImpl;
import cl.meli.technicalchallenge.application.UrlShortUseCaseImpl;
import cl.meli.technicalchallenge.domain.port.input.UrlDeleteUseCase;
import cl.meli.technicalchallenge.domain.port.input.UrlLongUseCase;
import cl.meli.technicalchallenge.domain.port.input.UrlShortUseCase;
import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.shared.ValidatorUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanCustomConfig {

  @Bean
  public UrlShortUseCase urlUseCaseImpl(UrlDomainRepository urlDomainRepository) {
    return new UrlShortUseCaseImpl(urlDomainRepository, new ValidatorUtils());
  }

  @Bean
  public UrlLongUseCase urlLongUseCaseImpl(UrlDomainRepository urlDomainRepository) {
    return new UrlLongUseCaseImpl(urlDomainRepository);
  }

  @Bean
  public UrlDeleteUseCase urlDeleteUseCase(UrlDomainRepository urlDomainRepository) {
    return new UrlDeleteUseCaseImpl(urlDomainRepository);
  }
}
