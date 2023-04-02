package cl.meli.technicalchallenge.domain.port.output;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;

public interface LogDomainRepository {
  void save(LogDomainModel logDomainModel);
}
