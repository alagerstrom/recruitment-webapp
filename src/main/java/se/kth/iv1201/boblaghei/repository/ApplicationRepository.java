package se.kth.iv1201.boblaghei.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.boblaghei.entity.Application;
import se.kth.iv1201.boblaghei.entity.Competence;
import se.kth.iv1201.boblaghei.util.ApplicationSearchDTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Repository responsible for basic CRUD-Operations concerning <code>Application</code> interaction with the database.
 */
public interface ApplicationRepository extends CrudRepository<Application, Long> {

}
