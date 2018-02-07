package se.kth.iv1201.boblaghei.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.boblaghei.entity.Status;

/**
 * Repository responsible for basic CRUD-Operations concerning <code>Status</code> interaction with the database.
 */
public interface StatusRepository extends CrudRepository<Status, Long>{
    Status getByName(String name);
}
