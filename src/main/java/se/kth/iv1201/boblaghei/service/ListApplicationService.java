package se.kth.iv1201.boblaghei.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.iv1201.boblaghei.dto.ApplicationDTO;
import se.kth.iv1201.boblaghei.entity.Application;
import se.kth.iv1201.boblaghei.exception.ResourceNotFoundException;
import se.kth.iv1201.boblaghei.repository.ApplicationRepository;
import se.kth.iv1201.boblaghei.repository.CompetenceProfileRepository;
import se.kth.iv1201.boblaghei.util.ApplicationSearchDTO;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Service containing business logic needed for searching and listing applications.
 */
@Service
public class ListApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    CompetenceProfileRepository competenceProfileRepository;

    @Autowired
    EntityManager entityManager;


    //TODO Decide if specification should be done here by calling several repositories and creating the parameters here
    //or in the applicationRepository, and create a specific query that can handle the param applicationSearchDTO directly.
    public List<ApplicationDTO> findApplications(ApplicationSearchDTO applicationSearchDTO) {
        Session session = entityManager.unwrap(Session.class);

        List<Application> foundApplications = new ArrayList<>(session.createQuery(applicationSearchDTO.toSQL()).list());

        List<ApplicationDTO> foundApplicationDTOs = new ArrayList<>();

        for (Application a : foundApplications) {
            foundApplicationDTOs.add(a.getDTO());
        }

        return foundApplicationDTOs;
    }


    public ApplicationDTO findApplicationById(long id) {
        Application application = applicationRepository.findOne(id);
        if (application == null)
            throw new ResourceNotFoundException("Could not find application with id " + id);
        return application.getDTO();
    }
}
