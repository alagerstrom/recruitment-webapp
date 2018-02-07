package se.kth.iv1201.boblaghei.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.boblaghei.entity.Application;
import se.kth.iv1201.boblaghei.entity.CompetenceProfile;

import java.util.List;

/**
 * Repository responsible for basic CRUD-Operations concerning <code>CompetenceProfile</code> interaction with the database.
 */
public interface CompetenceProfileRepository extends CrudRepository<CompetenceProfile, Long> {

    List<CompetenceProfile> getCompetenceProfilesByApplication(Application application);
}
