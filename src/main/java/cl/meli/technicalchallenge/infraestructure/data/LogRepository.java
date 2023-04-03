package cl.meli.technicalchallenge.infraestructure.data;

import cl.meli.technicalchallenge.infraestructure.data.dto.LogDto;
import cl.meli.technicalchallenge.infraestructure.data.entities.LogEntity;
import java.util.List;
import javax.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {

  //@OrderBy("ID DESC")
  List<LogEntity> findByShortUrlVisited(String shortUrl);

  @Query(value = "SELECT LOG.SHORT_URL_VISITED as shortUrlVisited, COUNT(LOG.SHORT_URL_VISITED) as timesVisited " +
      "FROM LOG " +
      "WHERE LOG.ACTIVE = TRUE " +
      "GROUP BY LOG.SHORT_URL_VISITED", nativeQuery = true)
  List<LogDto> countShortUrlVisit();

  @Query(value = "SELECT TOP 2 * FROM LOG " +
      "WHERE SHORT_URL_VISITED = ?1 " +
      "ORDER BY ID DESC;", nativeQuery = true)
  List<LogEntity> findShortUrlLastCalls(String shortUrl);
}
