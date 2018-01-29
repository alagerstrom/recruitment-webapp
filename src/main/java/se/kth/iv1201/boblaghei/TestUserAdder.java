package se.kth.iv1201.boblaghei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.entity.Role;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.entity.UserRole;
import se.kth.iv1201.boblaghei.repository.PersonRepository;
import se.kth.iv1201.boblaghei.repository.RoleRepository;
import se.kth.iv1201.boblaghei.repository.UserRepository;
import se.kth.iv1201.boblaghei.repository.UserRoleRepository;
import se.kth.iv1201.boblaghei.util.Constants;

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

        //roleRepository.save(role2);
        userRepository.save(user2);
        personRepository.save(person2);
        userRoleRepository.save(userRole2);
    }
}
