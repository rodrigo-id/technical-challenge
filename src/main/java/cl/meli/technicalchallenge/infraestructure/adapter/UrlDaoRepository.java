package cl.meli.technicalchallenge.infraestructure.adapter;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.domain.port.output.UrlDomainRepository;
import cl.meli.technicalchallenge.infraestructure.adapter.mapper.UrlRepositoryMapper;
import cl.meli.technicalchallenge.infraestructure.data.UrlRepository;
import org.springframework.stereotype.Component;

@Component
public class UrlDaoRepository implements UrlDomainRepository {

  private final UrlRepository urlRepository;
  private final UrlRepositoryMapper urlRepositoryMapper;

  public UrlDaoRepository(UrlRepository urlRepository, UrlRepositoryMapper urlRepositoryMapper) {
    this.urlRepository = urlRepository;
    this.urlRepositoryMapper = urlRepositoryMapper;
  }

  @Override
  public void saveUrl(UrlDomainModel urlDomainModel) {
    urlRepository.save(urlRepositoryMapper.toUrlEntity(urlDomainModel));
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
  public Long deleteShortUrl(String url) {
    return urlRepository.deleteByShortUrl(url);
  }
}
