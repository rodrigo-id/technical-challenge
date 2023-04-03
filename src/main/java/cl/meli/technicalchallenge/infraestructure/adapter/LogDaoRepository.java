package cl.meli.technicalchallenge.infraestructure.adapter;

import cl.meli.technicalchallenge.domain.model.LogDomainModel;
import cl.meli.technicalchallenge.domain.model.LogVisitDomainModel;
import cl.meli.technicalchallenge.domain.port.output.LogDomainRepository;
import cl.meli.technicalchallenge.infraestructure.adapter.mapper.LogRepositoryMapper;
import cl.meli.technicalchallenge.infraestructure.data.LogRepository;
import cl.meli.technicalchallenge.infraestructure.data.dto.ILogDto;
import cl.meli.technicalchallenge.infraestructure.data.entities.LogEntity;
import java.util.ArrayList;
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
  public void saveLongInfo(LogDomainModel logDomainModel) {
    logRepository.save(logRepositoryMapper.toLogEntity(logDomainModel));
  }

  @Override
  public List<LogDomainModel> retrieveShortUrlInfo(String url) {
    return logRepositoryMapper.toLogDomainModelList(logRepository.findByShortUrlVisited(url));
  }

  @Override
  public List<LogVisitDomainModel> retrieveTimesVisitForShortUrl() {
    List<ILogDto> iLogDto = logRepository.countShortUrlVisit();
    List<LogEntity> logEntityList = new ArrayList<>();
    iLogDto.forEach(
        shortUrl -> {
          logEntityList.addAll(logRepository.findShortUrlLastCalls(shortUrl.getShortUrlVisited()));
        }
    );
    return logRepositoryMapper.toLogVisitDomainModel(logEntityList, iLogDto);
  }


}
