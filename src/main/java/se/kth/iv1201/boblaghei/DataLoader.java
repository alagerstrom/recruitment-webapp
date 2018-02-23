package se.kth.iv1201.boblaghei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import se.kth.iv1201.boblaghei.entity.*;
import se.kth.iv1201.boblaghei.repository.*;
import se.kth.iv1201.boblaghei.util.Constants;

import java.util.Arrays;
import java.util.Date;

/**
 * DataLoader to load test data to the database
 */
@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CompetenceRepository competenceRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) {

        personRepository.deleteAll();
        // Roles
        // Users and Persons
        User user1 = new User("admin", "admin", true);
        Arrays.asList(Role.ROLE_APPLICANT, Role.ROLE_RECRUITER).forEach(role -> user1.getRoles().add(role));
        Person person1 = new Person("Admin-Stina", "Stinasson", "19670915-1234", "admin@stina.com", user1);
        user1.setPerson(person1);
        person1.setUser(user1);


        User user2 = new User("kalle", "kula", true);
        user2.getRoles().add(Role.ROLE_APPLICANT);
        Person person2 = new Person("Kalle", "Kula", "19770915-1234", "kalle@kula.com", user2);
        user2.setPerson(person2);
        person2.setUser(user2);

        User user3 = new User("bertil", "bertil", true);
        user3.getRoles().add(Role.ROLE_APPLICANT);
        Person person3 = new Person("Bertil", "Svensson", "19771212-1234", "bertil@svensson.com", user3);
        user3.setPerson(person3);
        person3.setUser(user3);

        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);

        // Statuses
        Status status = new Status(Constants.STATUS_UNHANDLED).addTranslation("de", "Unbehandelt");
        statusRepository.save(status);


        // Competences
        Competence competence1 = new Competence("Balloon seller").addTranslation("de","Ballonverkäufer");
        Competence competence2 = new Competence("Machine operator").addTranslation("de", "Maschinenbediener");
        Competence competence3 = new Competence("Ticket seller").addTranslation("de", "Ticketverkäufer");
        Competence competence4= new Competence("Technician").addTranslation("de", "Techniker");
        competenceRepository.save(competence1);
        competenceRepository.save(competence2);
        competenceRepository.save(competence3);
        competenceRepository.save(competence4);


        // Applications
        Application application1 = new Application(new Date(), status, person2);
        CompetenceProfile competenceProfile11 = new CompetenceProfile(3, application1, competence1);
        CompetenceProfile competenceProfile12 = new CompetenceProfile(3, application1, competence2);
        CompetenceProfile competenceProfile13 = new CompetenceProfile(3, application1, competence3);
        Availability availability1 = new Availability(new Date(), new Date(), application1);
        application1.getCompetenceProfiles().add(competenceProfile11);
        application1.getCompetenceProfiles().add(competenceProfile12);
        application1.getCompetenceProfiles().add(competenceProfile13);
        application1.getAvailabilities().add(availability1);
        applicationRepository.save(application1);

        Application application2 = new Application(new Date(), status, person3);
        CompetenceProfile competenceProfile21 = new CompetenceProfile(3, application2, competence1);
        CompetenceProfile competenceProfile22= new CompetenceProfile(3, application2, competence2);
        CompetenceProfile competenceProfile23 = new CompetenceProfile(3, application2, competence3);
        Availability availability2 = new Availability(new Date(), new Date(), application2);
        application2.getCompetenceProfiles().add(competenceProfile21);
        application2.getCompetenceProfiles().add(competenceProfile22);
        application2.getCompetenceProfiles().add(competenceProfile23);
        application2.getAvailabilities().add(availability2);
        applicationRepository.save(application2);
    }
}
