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
     * @param  A list of CompetenceProfileDTO that identifies the applicants different competences and
     *                           years of experience, the id and application field does not need to be set.
     * @param availalities     A list of AvailabilityDTO representing when the applicant is available.
     *                           The id and application fields does not need to be set.
     * @throws NoUserLoggedInException If no user is currently logged in.
     */
    @Transactional
    public void createApplicationForCurrentUser(Application newApplication) throws NoUserLoggedInException {
        newApplication.getCompetenceProfiles().forEach(competenceProfile -> competenceProfile.setApplication(newApplication));
        newApplication.getAvailabilities().forEach(availability -> availability.setApplication(newApplication));
        Person getLoggedInPerson = securityService.getLoggedInPerson();
        Status status = getUnhandledStatus();
        Date date = new Date();
        newApplication.setPerson(getLoggedInPerson);
        newApplication.setStatus(status);
        newApplication.setCreated(date);
        applicationRepository.save(newApplication);
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
