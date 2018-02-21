package se.kth.iv1201.boblaghei.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.kth.iv1201.boblaghei.entity.Application;
import se.kth.iv1201.boblaghei.exception.NoUserLoggedInException;
import se.kth.iv1201.boblaghei.util.HttpPath;
import se.kth.iv1201.boblaghei.service.CreateApplicationService;
import se.kth.iv1201.boblaghei.service.ListApplicationService;
import se.kth.iv1201.boblaghei.dto.ApplicationSearchDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(HttpPath.APPLICATIONS_PATH)
public class ApplicationResource {

    @Autowired
    ListApplicationService listApplicationService;

    @Autowired
    CreateApplicationService createApplicationService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_RECRUITER')")
    public List<Application> getAllApplications(@RequestBody ApplicationSearchDTO applicationSearchDTO) {
        return listApplicationService.findApplications(applicationSearchDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_RECRUITER')")
    public Application getSingleApplication(@PathVariable("id") long id){
        return listApplicationService.findApplicationById(id);
    }

    @PostMapping
    public Application createApplication(@Valid @RequestBody Application application) throws NoUserLoggedInException {
        return createApplicationService.createApplicationForCurrentUser(application);
    }
}
