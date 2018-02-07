package se.kth.iv1201.boblaghei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.iv1201.boblaghei.dto.AvailabilityDTO;
import se.kth.iv1201.boblaghei.dto.CompetenceDTO;
import se.kth.iv1201.boblaghei.dto.CompetenceProfileDTO;
import se.kth.iv1201.boblaghei.dto.PersonDTO;
import se.kth.iv1201.boblaghei.entity.*;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.repository.*;
import se.kth.iv1201.boblaghei.util.Constants;

import java.util.*;

/**
 * CreateApplicationService
 * Service that can be used to create a new Application.
 */
@Service
public class CreateApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

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
     * @throws NoUserLoggedInException If no user is currently logged in.
     */
    public void createApplicationForCurrentUser(
            List<CompetenceProfileDTO> competenceProfiles,
            List<AvailabilityDTO> availabilities)
            throws NoUserLoggedInException {
        PersonDTO personDTO = registerService.getLoggedInPerson();
        if (personDTO == null)
            throw new NoUserLoggedInException();
        Person person = personRepository.findOne(personDTO.getId());
        if (person == null)
            throw new NoUserLoggedInException();
        Status status = getUnhandledStatus();
        Date date = new Date();
        Application application = new Application(date, status, person);

        applicationRepository.save(application);

        Set<CompetenceProfile> competenceProfilesInApplication = new HashSet<>();

        for (CompetenceProfileDTO competenceProfileDTO : competenceProfiles) {
            Competence competence = competenceRepository.findOne(competenceProfileDTO.getCompetence().getId());
            CompetenceProfile competenceProfile = new CompetenceProfile(
                    competenceProfileDTO.getYearsOfExperience(),
                    application,
                    competence
            );
            competenceProfileRepository.save(competenceProfile);
            competenceProfilesInApplication.add(competenceProfile);
        }

        application.setCompetenceProfiles(competenceProfilesInApplication);

        Set<Availability> availabilitiesInApplication = new HashSet<>();

        for (AvailabilityDTO availabilityDTO : availabilities) {
            Availability availability = new Availability(
                    availabilityDTO.getFromDate(),
                    availabilityDTO.getToDate(),
                    application
            );
            availabilityRepository.save(availability);
            availabilitiesInApplication.add(availability);
        }

        application.setAvailabilities(availabilitiesInApplication);
        applicationRepository.save(application);

    }

    /**
     * listAllCompetences()
     * <p>
     * Used to get all the competences currently available in the database.
     *
     * @return A List of CompetenceDTO representing all the available competences.
     */
    public List<CompetenceDTO> listAllCompetences(){
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
