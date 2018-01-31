package se.kth.iv1201.boblaghei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.iv1201.boblaghei.dto.AvailabilityDTO;
import se.kth.iv1201.boblaghei.dto.CompetenceDTO;
import se.kth.iv1201.boblaghei.dto.CompetenceProfileDTO;
import se.kth.iv1201.boblaghei.dto.PersonDTO;
import se.kth.iv1201.boblaghei.entity.Application;
import se.kth.iv1201.boblaghei.entity.Competence;
import se.kth.iv1201.boblaghei.entity.Person;
import se.kth.iv1201.boblaghei.exception.ApplicationException;
import se.kth.iv1201.boblaghei.repository.CompetenceRepository;
import se.kth.iv1201.boblaghei.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * CreateApplicationService
 * <p>
 * Service that can be used to create a new Application.
 */

@Service
public class CreateApplicationService {

    @Autowired
    RegisterService registerService;

    @Autowired
    CompetenceRepository competenceRepository;

    @Autowired
    PersonRepository personRepository;

    /**
     * createApplicationForCurrentUser
     * <p>
     * Method to be used to create a new Application for the currently logged in user.
     *
     * @param competenceProfiles A list of CompetenceProfileDTO that identifies the applicants different competences and
     *                           years of experience, the id and application field does not need to be set.
     * @param availabilities     A list of AvailabilityDTO representing when the applicant is available.
     *                           The id and application fields does not need to be set.
     * @throws ApplicationException If no user is currently logged in.
     */
    public void createApplicationForCurrentUser(
            List<CompetenceProfileDTO> competenceProfiles,
            List<AvailabilityDTO> availabilities)
            throws ApplicationException {
        PersonDTO personDTO = registerService.getLoggedInPerson();
        Person person = personRepository.findOne(personDTO.getId());


        Application application = new Application()
    }

    /**
     * listAllCompetences()
     * <p>
     * Used to get all the competences currently available in the database.
     *
     * @return A List of CompetenceDTO representing all the available competences.
     * @throws ApplicationException
     */
    public List<CompetenceDTO> listAllCompetences() throws ApplicationException {
        List<CompetenceDTO> result = new ArrayList<>();
        for (Competence competence : competenceRepository.findAll()) {
            result.add(competence.getDTO());
        }
        return result;
    }
}
