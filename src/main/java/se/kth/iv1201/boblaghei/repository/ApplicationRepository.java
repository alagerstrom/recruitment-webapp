package se.kth.iv1201.boblaghei.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.iv1201.boblaghei.entity.Application;
import se.kth.iv1201.boblaghei.util.ApplicationSearchDTO;

import java.util.List;

public interface ApplicationRepository extends CrudRepository<Application, Long>{

    //TODO either add a custom query or a specification here, so that the applicationSearch can be implemented correctly.
    //Or add it in the ListApplicationService and use several repositories there.
}
