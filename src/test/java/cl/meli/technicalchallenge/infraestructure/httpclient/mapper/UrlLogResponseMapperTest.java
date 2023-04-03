package cl.meli.technicalchallenge.infraestructure.httpclient.mapper;

import cl.meli.technicalchallenge.infraestructure.httpclient.models.UrlLogResponse;
import cl.meli.technicalchallenge.mock.LogDomainModelMock;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UrlLogResponseMapperTest {

  UrlLogResponseMapper urlLogResponseMapper = new UrlLogResponseMapper();

  @Test
  void givenALisOfDomainLog_whenMapToALogResponse_thenReturnUrlLogResponse() {

    UrlLogResponse response = urlLogResponseMapper.toUrlLogResponse(
        List.of(LogDomainModelMock.buildNewModelForTest(), LogDomainModelMock.buildDeactivateModelForTest()));

    Assertions.assertNotNull(response);
  }
}