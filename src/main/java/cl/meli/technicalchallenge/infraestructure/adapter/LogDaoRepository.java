package cl.meli.technicalchallenge.infraestructure.adapter;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.domain.port.output.LogDomainRepository;
import cl.meli.technicalchallenge.infraestructure.adapter.mapper.LogRepositoryMapper;
import cl.meli.technicalchallenge.infraestructure.data.LogRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class LogDaoRepository implements LogDomainRepository {
  private final LogRepository logRepository;
  private final LogRepositoryMapper logRepositoryMapper;

  public LogDaoRepository(LogRepository logRepository, LogRepositoryMapper logRepositoryMapper) {
    this.logRepository = logRepository;
    this.logRepositoryMapper = logRepositoryMapper;
  }

  @Override
  public void save(LogDomainModel logDomainModel) {
    logRepository.save(logRepositoryMapper.toLogEntity(logDomainModel));
  }

  @Override
  public List<LogDomainModel> retrieveUrlInfo(String url) {
    return logRepositoryMapper.toLogDomainModelList(logRepository.findByShortUrlVisited(url));
  }

}
