package se.kth.iv1201.boblaghei.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.boblaghei.entity.Competence;

/**
 * Repository responsible for basic CRUD-Operations concerning <code>Competence</code> interaction with the database.
 */
public interface CompetenceRepository extends CrudRepository<Competence, Long>{

}
