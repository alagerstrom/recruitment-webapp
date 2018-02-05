package se.kth.iv1201.boblaghei.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.boblaghei.entity.Application;
import se.kth.iv1201.boblaghei.util.ApplicationSearchDTO;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, Long> {

}
