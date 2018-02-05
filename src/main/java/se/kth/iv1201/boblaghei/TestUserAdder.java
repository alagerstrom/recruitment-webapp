package se.kth.iv1201.boblaghei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import se.kth.iv1201.boblaghei.entity.*;
import se.kth.iv1201.boblaghei.repository.*;
import se.kth.iv1201.boblaghei.service.CreateApplicationService;
import se.kth.iv1201.boblaghei.util.Constants;

import java.util.Date;

@Component
public class TestUserAdder implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    StatusRepository statusRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Role role = new Role(Constants.ROLE_RECRUITER);
        Role role2 = new Role(Constants.ROLE_APPLICANT);
        User user = new User("admin", "admin", true);
        UserRole userRole = new UserRole(user, role);
        UserRole userRole1 = new UserRole(user, role2);
        Person person = new Person("Admin-Stina", "Stinasson", "19670915-1234", "admin@stina.com", user);

        roleRepository.save(role);
        roleRepository.save(role2);
        userRepository.save(user);
        personRepository.save(person);
        userRoleRepository.save(userRole);
        userRoleRepository.save(userRole1);

        User user2 = new User("kalle", "kula", true);
        UserRole userRole2 = new UserRole(user2, role2);
        Person person2 = new Person("Kalle", "Kula", "19770915-1234", "kalle@kula.com", user2);

        userRepository.save(user2);
        personRepository.save(person2);
        userRoleRepository.save(userRole2);

        User user3 = new User("bertil", "bertil", true);
        UserRole userRole3 = new UserRole(user3, role2);
        Person person3 = new Person("Bertil", "Svensson", "19771212-1234", "bertil@svensson.com", user3);

        userRepository.save(user3);
        userRoleRepository.save(userRole3);
        personRepository.save(person3);
        createTestApplicationFor(person2, person3);

    }

    private void createTestApplicationFor(Person... persons) {
        Status status = new Status(Constants.STATUS_UNHANDLED);
        statusRepository.save(status);
        for (Person person : persons){
            Application application = new Application(new Date(), status, person);
            applicationRepository.save(application);
        }

    }
}
