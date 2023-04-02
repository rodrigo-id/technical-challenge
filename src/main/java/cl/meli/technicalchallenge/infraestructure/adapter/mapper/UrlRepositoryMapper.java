package cl.meli.technicalchallenge.infraestructure.adapter.mapper;

import cl.meli.technicalchallenge.domain.model.UrlDomainModel;
import cl.meli.technicalchallenge.infraestructure.data.relational.entity.UrlEntity;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class UrlRepositoryMapper {
  public UrlEntity toUrlEntity(UrlDomainModel urlDomainModel) {
    UrlEntity urlEntity = new UrlEntity();
    urlEntity.setLongUrl(urlDomainModel.getLongUrl());
    urlEntity.setShortUrl(urlDomainModel.getShortUrl());
    urlEntity.setCreatedDate(urlDomainModel.getCreatedDate());

    return urlEntity;
  }

  public UrlDomainModel toUrlDomainModel(Optional<UrlEntity> urlEntity) {
    if (urlEntity.isPresent()) {
      return UrlDomainModel.builder()
          .setLongUrl(urlEntity.get().getLongUrl())
          .setShortUrl(urlEntity.get().getShortUrl())
          .setIsPresent(true)
          .build();
    }
    return UrlDomainModel.builder().build();
  }
}
