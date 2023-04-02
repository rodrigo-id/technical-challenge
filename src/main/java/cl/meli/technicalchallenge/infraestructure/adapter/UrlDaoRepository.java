package cl.meli.technicalchallenge.infraestructure.adapter;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.infraestructure.adapter.mapper.UrlRepositoryMapper;
import cl.meli.technicalchallenge.infraestructure.data.relational.UrlRepository;
import java.util.Objects;
import javax.transaction.Transactional;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class UrlDaoRepository implements UrlDomainRepository {

  private static final String CACHE_DATA = "urlData";
  private final UrlRepository urlRepository;
  private final UrlRepositoryMapper urlRepositoryMapper;
  private final CacheManager cacheManager;

  public UrlDaoRepository(UrlRepository urlRepository, UrlRepositoryMapper urlRepositoryMapper,
                          CacheManager cacheManager) {
    this.urlRepository = urlRepository;
    this.urlRepositoryMapper = urlRepositoryMapper;
    this.cacheManager = cacheManager;
  }

  @Override
  public void saveUrl(UrlDomainModel urlDomainModel) {
    urlRepository.save(urlRepositoryMapper.toUrlEntity(urlDomainModel));
  }

  @Override
  @Cacheable(value = CACHE_DATA, key = "#url", unless = "#result.isPresent == false")
  public UrlDomainModel findUrlByLongUrl(String url) {
    return urlRepositoryMapper.toUrlDomainModel(urlRepository.findByLongUrl(url));
  }

  @Override
  @Cacheable(value = CACHE_DATA, key = "#url", unless = "#result.isPresent == false")
  public UrlDomainModel findUrlByShortUrl(String url) {
    return urlRepositoryMapper.toUrlDomainModel(urlRepository.findByShortUrl(url));
  }

  @Override
  @Transactional
  public Long deleteShortUrl(UrlDomainModel urlDomain) {
    this.evictCache(urlDomain);
    return urlRepository.deleteByShortUrl(urlDomain.getShortUrl());
  }

  private void evictCache(UrlDomainModel urlDomainModel) {
    Objects.requireNonNull(cacheManager.getCache(CACHE_DATA)).evict(urlDomainModel.getShortUrl());
    Objects.requireNonNull(cacheManager.getCache(CACHE_DATA)).evict(urlDomainModel.getLongUrl());
  }


}
