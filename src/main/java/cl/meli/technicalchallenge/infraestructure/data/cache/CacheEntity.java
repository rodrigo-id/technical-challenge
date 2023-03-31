package cl.meli.technicalchallenge.infraestructure.data.cache;

import cl.meli.technicalchallenge.infraestructure.data.relational.entity.UrlEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("cacheEntityData")
public class CacheEntity {
  @Id
  private String key;

  private UrlEntity urlEntity;

  public UrlEntity getUrlEntity() {
    return urlEntity;
  }

  public void setUrlEntity(UrlEntity urlEntity) {
    this.urlEntity = urlEntity;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}

