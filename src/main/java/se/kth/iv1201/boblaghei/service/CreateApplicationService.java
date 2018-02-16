package se.kth.iv1201.boblaghei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.boblaghei.entity.*;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.exception.ResourceNotFoundException;
import se.kth.iv1201.boblaghei.repository.*;
import se.kth.iv1201.boblaghei.util.Constants;
import se.kth.iv1201.boblaghei.util.PdfGenerator;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
     * <p>
     * Method to be used to create a new Application for the currently logged in user.
     *
     * @param newApplication, the new application to be saved
     * @return The created Application
     * @throws NoUserLoggedInException If no user is currently logged in.
     */
    @Transactional
    public Application createApplicationForCurrentUser(Application newApplication) throws NoUserLoggedInException {
        newApplication.getCompetenceProfiles().forEach(competenceProfile -> competenceProfile.setApplication(newApplication));
        newApplication.getAvailabilities().forEach(availability -> availability.setApplication(newApplication));
        Person getLoggedInPerson = securityService.getLoggedInPerson();
        Status status = getUnhandledStatus();
        Date date = new Date();
        newApplication.setPerson(getLoggedInPerson);
        newApplication.setStatus(status);
        newApplication.setCreated(date);
        return applicationRepository.save(newApplication);
    }

    /**
     * listAllCompetences()
     * <p>
     * Used to get all the competences currently available in the database.
     *
     * @return A List of CompetenceDTO representing all the available competences.
     */

    @Transactional
    public Set<Competence> listAllCompetences() {
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

    public ByteArrayInputStream generatePdfFor(long applicationId) {
        Application application = applicationRepository.findOne(applicationId);
        if (application == null)
            throw new ResourceNotFoundException("Application with id " + applicationId + " does noe exist");
        return PdfGenerator.generateApplicationPdf(application);
    }
}
