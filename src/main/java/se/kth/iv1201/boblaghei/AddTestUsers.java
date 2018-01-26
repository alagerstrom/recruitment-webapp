package se.kth.iv1201.boblaghei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.Role;
import se.kth.iv1201.boblaghei.repository.PersonRepository;
import se.kth.iv1201.boblaghei.util.Constants;

@Component
public class AddTestUsers implements ApplicationRunner{

    @Autowired
    PersonRepository personRepository;


    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Role recruiter = new Role(Constants.RECRUITER_ROLE);

        Person recruiterPerson = new Person(
                "Ulf",
                "Karlsson",
                "19770315-1234",
                "ulf.karlsson@mylittlepony.com",
                "ulf",
                "uffe77",
                recruiter
        );
        personRepository.save(recruiterPerson);

        for (Person person : personRepository.findAll()){
            System.out.println(person);
        }
    }
}
