package se.kth.iv1201.boblaghei.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.boblaghei.entity.Application;

/**
 * Repository responsible for basic CRUD-Operations concerning <code>Application</code> interaction with the database.
 */
public interface ApplicationRepository extends CrudRepository<Application, Long> {

}
