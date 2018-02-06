package se.kth.iv1201.boblaghei.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.boblaghei.entity.User;
import se.kth.iv1201.boblaghei.entity.UserRole;

import java.util.Set;

/**
 * Repository responsible for basic CRUD-Operations concerning <code>UserRole</code> interaction with the database.
 */

public interface UserRoleRepository extends CrudRepository<UserRole, String> {

    Set<UserRole> getUserRolesByUser(User user);

}
