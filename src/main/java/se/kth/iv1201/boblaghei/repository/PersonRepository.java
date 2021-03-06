package se.kth.iv1201.boblaghei.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.User;

/**
 * Repository responsible for basic CRUD-Operations concerning <code>Person</code> interaction with the database.
 */
public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findPersonByUser(User user);

}
