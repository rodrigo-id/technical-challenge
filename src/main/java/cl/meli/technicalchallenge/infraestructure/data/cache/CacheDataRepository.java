package cl.meli.technicalchallenge.infraestructure.data.cache;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheDataRepository extends CrudRepository<CacheEntity, String> {
}
