package se.kth.iv1201.boblaghei.view.applications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.boblaghei.entity.Application;
import se.kth.iv1201.boblaghei.service.CreateApplicationService;
import se.kth.iv1201.boblaghei.service.ListApplicationService;
import se.kth.iv1201.boblaghei.util.ApplicationSearchDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Controller responsible for providing mappings used for searching and listing applications.
 */
@Controller
@RequestMapping("/recruiter/applications")
public class ListApplicationsView  {

    @Autowired
    private CreateApplicationService createApplicationService;

    @Autowired
    private ListApplicationService listApplicationService;

    /**
     * Invocated when a GET request is sent to "/recruiter/applications". Loads all competences from the database as well
     * as the competences that the user has chosen for search.
     * @param model is responsible for making data available in the view.
     * @return the listApplications.html page
     */
    @GetMapping
    public String applicationView(Model model) {
        model.addAttribute("availableCompetences", createApplicationService.listAllCompetences());
        model.addAttribute("applicationSearchDto", new ApplicationSearchDTO());
        return "listApplications";
    }

    /**
     * Invocated when a POST request is sent to "/recruiter/applications". Takes parameters from the search fields and
     * builds an <code>ApplicationSearchDTO</code> that is used in <code>findApplications</code> to return all applications
     * matching the search parameters. These are then loaded into the model and the view is loaded.
     * @param model is responsible for making data available in the view.
     * @param dto contains the search criteria
     * @return the return value from the applicationView method, see @applicationView.
     */
    @PostMapping
    public String search(Model model, @ModelAttribute ApplicationSearchDTO dto) {
        System.out.println(dto);
        List<Application> applications = listApplicationService.findApplications(dto);
        model.addAttribute("listOfApplications", applications);
        return applicationView(model);
    }

    /**
     * Invocated when a GET-request is sent to "/recruiter/applications/id". Responsible for loading a single application
     * view
     * @param model responsible for making data available in the view
     * @param id the id of the application to show
     * @return the singleApplication.html page
     */
    @GetMapping("/{id}")
    public String viewSingleApplication(Model model, @PathVariable("id") long id) {
        Application application = listApplicationService.findApplicationById(id);
        model.addAttribute("singleApplication", application);
        return "singleApplication";
    }

    @GetMapping(value = "/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getApplicationAsPDF(@PathVariable("id") long applicationId){
        ByteArrayInputStream bis = createApplicationService.generatePdfFor(applicationId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-disposition", "inline: filename=application.pdf");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
    }
}
