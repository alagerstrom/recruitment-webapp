package se.kth.iv1201.boblaghei.view;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.kth.iv1201.boblaghei.dto.AvailabilityDTO;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsible for providing mappings used for creating applications.
 */
@Controller
@RequestMapping("/apply")
public class CreateApplicationView extends AbstractApplicationView {

    private List<AvailabilityDTO> availabilities = new ArrayList<>();

    @Override
    @GetMapping
    public String applicationView(Model model) {
        availableCompetences = createApplicationService.listAllCompetences();
        model.addAttribute("availabilities", availabilities);
        model.addAttribute("availableCompetences", availableCompetences);
        model.addAttribute("selectedCompetences", selectedCompetences);
        return "apply";
    }

    @PostMapping("/add-availability")
    public String addAvailability(
            Model model,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        AvailabilityDTO availabilityDTO = new AvailabilityDTO(DateUtil.getDateFrom(from), DateUtil.getDateFrom(to), null);
        availabilities.add(availabilityDTO);
        System.out.println(availabilityDTO);
        return applicationView(model);
    }

    @PostMapping("/submit-application")
    public String submitApplication(Model model) {
        try {
            createApplicationService.createApplicationForCurrentUser(selectedCompetences, availabilities);
        } catch (NoUserLoggedInException e) {
            errorLogger.log(e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
