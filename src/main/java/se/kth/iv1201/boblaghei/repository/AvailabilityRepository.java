package se.kth.iv1201.boblaghei.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.boblaghei.entity.Availability;

/**
 * Repository responsible for basic CRUD-Operations concerning <code>Availability</code> interaction with the database.
 */
public interface AvailabilityRepository extends CrudRepository<Availability, Long> {

}
