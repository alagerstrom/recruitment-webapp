package se.kth.iv1201.boblaghei.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.boblaghei.dto.*;
import se.kth.iv1201.boblaghei.entity.Application;
import se.kth.iv1201.boblaghei.entity.Availability;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.exception.ResourceNotFoundException;
import se.kth.iv1201.boblaghei.repository.ApplicationRepository;
import se.kth.iv1201.boblaghei.repository.CompetenceProfileRepository;
import se.kth.iv1201.boblaghei.util.ApplicationSearchDTO;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Service containing business logic needed for searching and listing applications.
 */
@Service
public class ListApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CompetenceProfileRepository competenceProfileRepository;

    @Autowired
    private EntityManager entityManager;

    /**
     * findApplication finds applications matching the parameters stated in the <code>ApplicationSearchDTO</code> and
     * returns these applications in a list.
     * @param dto holds search criterias based on what the user has requested.
     * @return a list of <code>ApplicationDTO</code> containing the matching applications.
     */

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<ApplicationDTO> findApplications(ApplicationSearchDTO dto) {
        Criteria criteria = entityManager.unwrap(Session.class).createCriteria(Application.class, "a");
        if (hasPersonPropertiesSet(dto)) {
            criteria.createAlias("a.person", "p");
            if (dto.getApplicantFirstname() != null && !dto.getApplicantFirstname().equals(""))
                criteria.add(Restrictions.eq("p.firstName", dto.getApplicantFirstname()));
            if (dto.getApplicantLastname() != null && !dto.getApplicantLastname().equals(""))
                criteria.add(Restrictions.eq("p.lastName", dto.getApplicantLastname()));
        }

        if (dto.getApplicationCreated() != null)
            criteria.add(Restrictions.eq("a.created", dto.getApplicationCreated()));

        if (hasAvailabilitiesSet(dto)) {
            criteria.createAlias("a.availabilities", "ava");
            if (dto.getAvailableFrom() != null)
                criteria.add(Restrictions.le("ava.fromDate", dto.getAvailableFrom()));
            if (dto.getAvailableTo() != null)
                criteria.add(Restrictions.ge("ava.toDate", dto.getAvailableTo()));
        }

        if (dto.getCompetence() != null) {
            criteria.createAlias("a.competenceProfiles", "cp");
            criteria.createAlias("cp.competence", "competence");
            criteria.add(Restrictions.eq("competence.name", dto.getCompetence()));
        }

        return (List<ApplicationDTO>) criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
    }

    /**
     * Finds an application given the applications id.
     * @param id the id of the application.
     * @return the application with the given id in the form of a <code>ApplicationDTO</code>.
     * @throws ResourceNotFoundException if there is no application with the given id.
     */

    // TODO: We REALLY need to consider the if DTO pattern is necessary for our application!! This is horrible!
    @Transactional(readOnly = true)
    public ApplicationDTO findApplicationById(long id) {
        Application application = applicationRepository.findOne(id);
        if (application == null)
            throw new ResourceNotFoundException("Could not find application with id " + id);
        StatusDTO statusDTO = new StatusDTO(application.getStatus().getId(), application.getStatus().getName());

        List<CompetenceProfileDTO> competenceProfiles = application.getCompetenceProfiles().stream().map(profile -> {
            CompetenceDTO competenceDTO = new CompetenceDTO(profile.getCompetence().getId(), profile.getCompetence().getName());
            return new CompetenceProfileDTO(profile.getId(), profile.getYearsOfExperience(), competenceDTO);
        }).collect(Collectors.toList());

        List<AvailabilityDTO> availabilities = application.getAvailabilities().stream().map(availability ->
                new AvailabilityDTO(availability.getId(), availability.getFromDate(), availability.getToDate())).collect(Collectors.toList());

        Person person = application.getPerson();
        PersonDTO personDTO = new PersonDTO(person.getId(), person.getFirstName(), person.getLastName(), person.getPersonalNumber(), person.getEmail());
        ApplicationDTO dto = new ApplicationDTO(application.getId(), application.getCreated(), statusDTO, personDTO, competenceProfiles, availabilities);
        return dto;
    }


    private boolean hasAvailabilitiesSet(ApplicationSearchDTO dto) {
        return dto.getAvailableFrom() != null || dto.getAvailableTo() != null;
    }

    private boolean hasPersonPropertiesSet(ApplicationSearchDTO dto) {
        return (dto.getApplicantFirstname() != null && !dto.getApplicantFirstname().equals("")) ||
                (dto.getApplicantLastname() != null && !dto.getApplicantLastname().equals(""));
    }
}
