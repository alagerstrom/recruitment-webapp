package se.kth.iv1201.boblaghei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.iv1201.boblaghei.dto.AvailabilityDTO;
import se.kth.iv1201.boblaghei.dto.CompetenceDTO;
import se.kth.iv1201.boblaghei.dto.CompetenceProfileDTO;
import se.kth.iv1201.boblaghei.dto.PersonDTO;
import se.kth.iv1201.boblaghei.entity.*;
import se.kth.iv1201.boblaghei.exception.ApplicationException;
import se.kth.iv1201.boblaghei.repository.*;
import se.kth.iv1201.boblaghei.util.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CreateApplicationService
 * <p>
 * Service that can be used to create a new Application.
 */

@Service
public class CreateApplicationService {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CompetenceProfileRepository competenceProfileRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

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
        if (personDTO == null)
            throw new ApplicationException("No user logged in");
        Person person = personRepository.findOne(personDTO.getId());
        if (person == null)
            throw new ApplicationException("No user logged in");
        Status status = getUnhandledStatus();
        Date date = new Date();
        Application application = new Application(date, status, person);

        for (CompetenceProfileDTO competenceProfileDTO : competenceProfiles) {
            Competence competence = competenceRepository.findOne(competenceProfileDTO.getCompetence().getId());
            CompetenceProfile competenceProfile = new CompetenceProfile(
                    competenceProfileDTO.getYearsOfExperience(),
                    application,
                    competence
            );
            competenceProfileRepository.save(competenceProfile);
        }

        for (AvailabilityDTO availabilityDTO : availabilities) {
            Availability availability = new Availability(
                    availabilityDTO.getFrom(),
                    availabilityDTO.getTo(),
                    application
            );
            availabilityRepository.save(availability);
        }
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

    /**
     * Will check if the database contains the status 'unhandled',
     * and insert it otherwise.
     *
     * @return The status 'unhandled'
     */
    private Status getUnhandledStatus() {
        Status status = statusRepository.getByName(Constants.STATUS_UNHANDLED);
        if (status == null) {
            status = new Status(Constants.STATUS_UNHANDLED);
            statusRepository.save(status);
        }
        return status;
    }
}
