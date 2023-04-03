package cl.meli.technicalchallenge.infraestructure.data;

import cl.meli.technicalchallenge.infraestructure.data.entities.LogEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {

  List<LogEntity> findByShortUrlVisited(String shortUrl);
}
