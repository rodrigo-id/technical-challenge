package cl.meli.technicalchallenge.infraestructure.data.urlstorage;

import cl.meli.technicalchallenge.infraestructure.data.urlstorage.entities.UrlEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
  Optional<UrlEntity> findByLongUrl(String longUrl);
  Optional<UrlEntity> findByShortUrl(String shortUrl);
  Long deleteByShortUrl(String shortUrl);
}
