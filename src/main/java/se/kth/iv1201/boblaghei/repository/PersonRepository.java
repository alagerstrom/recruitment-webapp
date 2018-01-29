package se.kth.iv1201.boblaghei.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.boblaghei.dto.PersonDTO;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.User;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Person getPersonByUser(User user);
}
