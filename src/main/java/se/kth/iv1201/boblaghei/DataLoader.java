package se.kth.iv1201.boblaghei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import se.kth.iv1201.boblaghei.entity.*;
import se.kth.iv1201.boblaghei.repository.*;
import se.kth.iv1201.boblaghei.util.Constants;

import java.util.Date;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private CompetenceProfileRepository competenceProfileRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) {
        // Roles
        Role recruiterRole = new Role(Constants.ROLE_RECRUITER);
        Role applicantRole = new Role(Constants.ROLE_APPLICANT);
        roleRepository.save(recruiterRole);
        roleRepository.save(applicantRole);

        // Users and Persons
        User user1 = new User("admin", "admin", true);
        UserRole user1Role1 = new UserRole(user1, recruiterRole);
        UserRole user1Role2 = new UserRole(user1, applicantRole);
        Person person1 = new Person("Admin-Stina", "Stinasson", "19670915-1234", "admin@stina.com", user1);
        userRepository.save(user1);
        userRoleRepository.save(user1Role1);
        userRoleRepository.save(user1Role2);
        personRepository.save(person1);

        User user2 = new User("kalle", "kula", true);
        UserRole user2Role1 = new UserRole(user2, applicantRole);
        Person person2 = new Person("Kalle", "Kula", "19770915-1234", "kalle@kula.com", user2);
        userRepository.save(user2);
        userRoleRepository.save(user2Role1);
        personRepository.save(person2);

        User user3 = new User("bertil", "bertil", true);
        UserRole user3Role1 = new UserRole(user3, applicantRole);
        Person person3 = new Person("Bertil", "Svensson", "19771212-1234", "bertil@svensson.com", user3);
        userRepository.save(user3);
        userRoleRepository.save(user3Role1);
        personRepository.save(person3);

        // Statuses
        Status status = new Status(Constants.STATUS_UNHANDLED);
        statusRepository.save(status);


        // Competences
        Competence competence1 = new Competence("Ballongförsäljare");
        Competence competence2 = new Competence("Maskinoperatör");
        Competence competence3 = new Competence("Biljettförsälare");
        Competence competence4= new Competence("Reparatör");
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

        applicationRepository.save(application1);
        competenceProfileRepository.save(competenceProfile11);
        competenceProfileRepository.save(competenceProfile12);
        competenceProfileRepository.save(competenceProfile13);
        availabilityRepository.save(availability1);

        Application application2 = new Application(new Date(), status, person3);
        CompetenceProfile competenceProfile21 = new CompetenceProfile(3, application2, competence1);
        CompetenceProfile competenceProfile22= new CompetenceProfile(3, application2, competence2);
        CompetenceProfile competenceProfile23 = new CompetenceProfile(3, application2, competence3);
        Availability availability2 = new Availability(new Date(), new Date(), application2);


        applicationRepository.save(application2);
        competenceProfileRepository.save(competenceProfile21);
        competenceProfileRepository.save(competenceProfile22);
        competenceProfileRepository.save(competenceProfile23);
        availabilityRepository.save(availability2);
    }
}
