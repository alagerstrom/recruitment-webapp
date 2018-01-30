package se.kth.iv1201.boblaghei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.repository.PersonRepository;

@Service
class PersonService extends AbstractService<Person> {

    @Autowired
    PersonRepository personRepository;

    @Override
    CrudRepository getTRepository() {
        return personRepository;
    }
}
