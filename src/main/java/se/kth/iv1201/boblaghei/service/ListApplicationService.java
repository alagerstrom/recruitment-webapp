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

    /**
     * findApplication finds applications matching the parameters stated in the <code>ApplicationSearchDTO</code> and
     * returns these applications in a list.
     * @param applicationSearchDTO holds search criterias based on what the user has requested.
     * @return a list of <code>ApplicationDTO</code> containing the matching applications.
     */
    public List<ApplicationDTO> findApplications(ApplicationSearchDTO applicationSearchDTO) {
        Session session = entityManager.unwrap(Session.class);

        List<Application> foundApplications = new ArrayList<>(session.createQuery(applicationSearchDTO.toSQL()).list());

        List<ApplicationDTO> foundApplicationDTOs = new ArrayList<>();

        for (Application a : foundApplications) {
            foundApplicationDTOs.add(a.getDTO());
        }

        return foundApplicationDTOs;
    }

    /**
     * Finds an application given the applications id.
     * @param id the id of the application.
     * @return the application with the given id in the form of a <code>ApplicationDTO</code>.
     * @throws ResourceNotFoundException if there is no application with the given id.
     */
    public ApplicationDTO findApplicationById(long id) {
        Application application = applicationRepository.findOne(id);
        if (application == null)
            throw new ResourceNotFoundException("Could not find application with id " + id);
        return application.getDTO();
    }
}
