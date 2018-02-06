package se.kth.iv1201.boblaghei.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.boblaghei.entity.Role;

/**
 * Repository responsible for basic CRUD-Operations concerning <code>Role</code> interaction with the database.
 */

public interface RoleRepository extends CrudRepository<Role, String>{

}
