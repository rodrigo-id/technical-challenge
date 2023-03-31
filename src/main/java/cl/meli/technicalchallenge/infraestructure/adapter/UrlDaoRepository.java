package cl.meli.technicalchallenge.infraestructure.adapter;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.infraestructure.adapter.mapper.UrlRepositoryMapper;
import cl.meli.technicalchallenge.infraestructure.data.cache.CacheDataRepository;
import cl.meli.technicalchallenge.infraestructure.data.cache.CacheEntity;
import cl.meli.technicalchallenge.infraestructure.data.relational.UrlRepository;
import cl.meli.technicalchallenge.infraestructure.data.relational.entity.UrlEntity;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class UrlDaoRepository implements UrlDomainRepository {

  private final UrlRepository urlRepository;
  private final UrlRepositoryMapper urlRepositoryMapper;

  private final CacheDataRepository cacheDataRepository;

  public UrlDaoRepository(UrlRepository urlRepository, UrlRepositoryMapper urlRepositoryMapper,
                          CacheDataRepository cacheDataRepository) {
    this.urlRepository = urlRepository;
    this.urlRepositoryMapper = urlRepositoryMapper;
    this.cacheDataRepository = cacheDataRepository;
  }

  @Override
  public void saveUrl(UrlDomainModel urlDomainModel) {
    UrlEntity urlEntity = urlRepositoryMapper.toUrlEntity(urlDomainModel);
    urlRepository.save(urlEntity);

    CacheEntity cacheEntity = new CacheEntity();
    cacheDataRepository.save(cacheEntity);
  }

  @Override
  public UrlDomainModel findUrlByLongUrl(String url) {
    return urlRepositoryMapper.toUrlDomainModel(urlRepository.findByLongUrl(url));
  }

  @Override
  public UrlDomainModel findUrlByShortUrl(String url) {
    return urlRepositoryMapper.toUrlDomainModel(urlRepository.findByShortUrl(url));
  }

  @Override
  @Transactional
  public Long deleteShortUrl(String url) {
    return urlRepository.deleteByShortUrl(url);
  }
}
