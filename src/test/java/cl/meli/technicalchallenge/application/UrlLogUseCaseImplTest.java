package cl.meli.technicalchallenge.application;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import cl.meli.technicalchallenge.domain.port.output.LogDomainRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UrlLogUseCaseImplTest {
  UrlLogUseCaseImpl urlLogUseCase;

  @Mock
  LogDomainRepository logDomainRepository;

  @BeforeEach
  void setUp() {
    urlLogUseCase = new UrlLogUseCaseImpl(logDomainRepository);
  }

  @Test
  void givenAUrlIsProvided_whenMethodSave_thenOk() {
    String url = "http://short.url/dadada";
    urlLogUseCase.save(url);

    verify(
        logDomainRepository,
        times(1)).save(url);
  }
}