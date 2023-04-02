package cl.meli.technicalchallenge.infraestructure.data.urlstorage;

import cl.meli.technicalchallenge.infraestructure.data.urlstorage.entities.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, Long> {
}
