package se.kth.iv1201.boblaghei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private CompetenceRepository competenceRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private SecurityService securityService;

    /**
     * createApplicationForCurrentUser
     *
     * Method to be used to create a new Application for the currently logged in user.
     *
     * @param competenceProfiles A list of CompetenceProfileDTO that identifies the applicants different competences and
     *                           years of experience, the id and application field does not need to be set.
     * @param availabilities     A list of AvailabilityDTO representing when the applicant is available.
     *                           The id and application fields does not need to be set.
     * @throws NoUserLoggedInException If no user is currently logged in.
     */
    @Transactional
    public void createApplicationForCurrentUser(
            Set<CompetenceProfile> competenceProfiles,
            Set<Availability> availabilities)
            throws NoUserLoggedInException {
        Person person = securityService.getLoggedInPerson();
        Status status = getUnhandledStatus();
        Date date = new Date();
        Application application = new Application(date, status, person);
        application.setCompetenceProfiles(competenceProfiles);
        application.setAvailabilities(availabilities);

        for (CompetenceProfile competenceProfile : application.getCompetenceProfiles()) {
            competenceProfile.setApplication(application);
        }


        for (Availability availability : application.getAvailabilities()) {
            availability.setApplication(application);
        }

        applicationRepository.save(application);
    }

    /**
     * listAllCompetences()
     * <p>
     * Used to get all the competences currently available in the database.
     *
     * @return A List of CompetenceDTO representing all the available competences.
     */

    @Transactional
    public Set<Competence> listAllCompetences(){
        Set<Competence> listOfCompetences = new HashSet<>();
        competenceRepository.findAll().forEach(listOfCompetences::add);
        return listOfCompetences;
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
