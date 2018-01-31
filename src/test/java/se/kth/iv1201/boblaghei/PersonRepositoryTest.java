package se.kth.iv1201.boblaghei;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.repository.PersonRepository;

import static org.assertj.core.api.Assertions.*;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class PersonRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void whenGetById() {

        //given
        User user = new User("stig","peppe",true);
        Person person = new Person();
        person.setFirstName("Stig");
        person.setLastName("Larsson");
        person.setEmail("stig@larsson.se");
        person.setPersonalNumber("12345");
        person.setUser(user);
        entityManager.persist(user);
        entityManager.persist(person);
        entityManager.flush();

        Person fetchedPerson = personRepository.getPersonByUser(user);

        assertThat(person.getId()).isEqualTo(fetchedPerson.getId());
        assertThat(person.getFirstName()).isEqualTo(fetchedPerson.getFirstName());
        assertThat(person.getEmail()).isEqualTo(fetchedPerson.getEmail());
    }
}
