package se.kth.iv1201.boblaghei.view;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.kth.iv1201.boblaghei.dto.ApplicationDTO;
import se.kth.iv1201.boblaghei.dto.AvailabilityDTO;
import se.kth.iv1201.boblaghei.exception.ApplicationException;
import se.kth.iv1201.boblaghei.util.ApplicationSearchDTO;
import se.kth.iv1201.boblaghei.util.DateUtil;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/recruiter/applications")
public class ListApplicationsView extends AbstractApplicationView {

    @GetMapping
    public String applicationView(Model model) {
        try {
            availableCompetences = createApplicationService.listAllCompetences();
        } catch (ApplicationException e) {
            errorLogger.log(e.getMessage());
            e.printStackTrace();
        }
        model.addAttribute("availableCompetences", availableCompetences);
        model.addAttribute("selectedCompetences", selectedCompetences);
        return "listApplications";
    }

    @PostMapping
    public String search(Model model,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        System.out.println("From: " + from);
        System.out.println("To " + to);
        System.out.println(Arrays.toString(selectedCompetences.toArray()));
        List<ApplicationDTO> applications = listApplicationService.findApplications(
                new ApplicationSearchDTO.Builder()
                        .setAvailableFrom(DateUtil.getDateFrom(from))
                        .setAvailableTo(DateUtil.getDateFrom(to))
                        .build()
        );
        model.addAttribute("listOfApplications", applications);
        return applicationView(model);
    }
}
