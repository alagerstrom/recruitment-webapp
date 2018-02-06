package se.kth.iv1201.boblaghei.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.boblaghei.entity.User;

/**
 * Repository responsible for basic CRUD-Operations concerning <code>User</code> interaction with the database.
 */

public interface UserRepository extends CrudRepository<User, String>{

    User getUserByUsername(String username);
}
