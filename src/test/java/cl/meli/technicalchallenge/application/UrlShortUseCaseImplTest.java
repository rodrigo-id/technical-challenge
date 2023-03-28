package cl.meli.technicalchallenge.application;

import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.shared.ValidatorUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UrlShortUseCaseImplTest {

  UrlShortUseCaseImpl urlShortUseCase;
  @Mock
  UrlDomainRepository urlDomainRepository;
  @Spy
  ValidatorUtils validatorUtils;

  @BeforeEach
  void setUp() {
    urlShortUseCase = new UrlShortUseCaseImpl(urlDomainRepository, validatorUtils);
  }

  @Test
  void given_when_then() {

  }
}