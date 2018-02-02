package se.kth.iv1201.boblaghei.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.kth.iv1201.boblaghei.dto.ApplicationDTO;
import se.kth.iv1201.boblaghei.dto.AvailabilityDTO;
import se.kth.iv1201.boblaghei.dto.CompetenceDTO;
import se.kth.iv1201.boblaghei.dto.CompetenceProfileDTO;
import se.kth.iv1201.boblaghei.exception.ApplicationException;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.service.CreateApplicationService;
import se.kth.iv1201.boblaghei.service.ListApplicationService;
import se.kth.iv1201.boblaghei.util.ApplicationSearchDTO;
import se.kth.iv1201.boblaghei.util.logger.ErrorLogger;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationView {

    @Autowired
    private CreateApplicationService createApplicationService;

    @Autowired
    private ListApplicationService listApplicationService;

    @Autowired
    private ErrorLogger errorLogger;

    private List<CompetenceProfileDTO> selectedCompetences = new ArrayList<>();
    private List<CompetenceDTO> availableCompetences;
    private List<AvailabilityDTO> availabilities = new ArrayList<>();

    @GetMapping("/apply")
    public String getApplicationView(Model model) {
        try {
            availableCompetences = createApplicationService.listAllCompetences();
        } catch (ApplicationException e) {
            errorLogger.log(e.getMessage());
            e.printStackTrace();
        }
        model.addAttribute("availabilities", availabilities);
        model.addAttribute("availableCompetences", availableCompetences);
        model.addAttribute("selectedCompetences", selectedCompetences);
        return "apply";
    }

    @PostMapping("/add-competence")
    public String addCompetence(Model model, @RequestParam long id, @RequestParam int yearsOfExperience) {
        selectedCompetences.add(new CompetenceProfileDTO(yearsOfExperience, null, getCompetenceById(id)));
        return getApplicationView(model);
    }

    @PostMapping("/add-availability")
    public String addAvailability(
            Model model,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        System.out.println(from);
        System.out.println(to);


        Date fromDate = Date.from(from.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date toDate = Date.from(to.atStartOfDay(ZoneId.systemDefault()).toInstant());


        AvailabilityDTO availabilityDTO = new AvailabilityDTO(fromDate, toDate, null);
        availabilities.add(availabilityDTO);
        System.out.println(availabilityDTO);
        return getApplicationView(model);
    }

    @PostMapping("/submit-application")
    public String submitApplication(Model model){
        try {
            createApplicationService.createApplicationForCurrentUser(selectedCompetences, availabilities);
        } catch (NoUserLoggedInException e) {
            errorLogger.log(e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/list-applications")
    public String listApplications(Model model) {
        try {
            availableCompetences = createApplicationService.listAllCompetences();
        } catch (ApplicationException e) {
            errorLogger.log(e.getMessage());
            e.printStackTrace();
        }
        model.addAttribute("availabilities", availabilities);
        model.addAttribute("availableCompetences", availableCompetences);
        model.addAttribute("selectedCompetences", selectedCompetences);
        return "listApplications";
    }

    @PostMapping("/list-applications")
    public String submitApplicationSearch(Model model) {
        List<ApplicationDTO> applications = listApplicationService.findApplications(
                new ApplicationSearchDTO.Builder()
//                        .setAvailableFrom(availabilities.get(0).getFrom())
//                        .setAvailableTo(availabilities.get(0).getTo())
                        .build()
        );
        model.addAttribute("listOfApplications", applications);
        return listApplications(model);

    }

    private CompetenceDTO getCompetenceById(long id) {
        for (CompetenceDTO competenceDTO : availableCompetences)
            if (competenceDTO.getId() == id)
                return competenceDTO;
        return null;
    }
}
